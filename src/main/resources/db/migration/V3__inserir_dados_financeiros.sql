INSERT INTO modalidades (nome)
VALUES ('Boxe'), ('Alongamento')
ON CONFLICT (nome) DO NOTHING;

INSERT INTO graduacoes (modalidade_id, nome)
SELECT modalidade.id, 'Iniciante'
FROM modalidades modalidade
WHERE modalidade.nome IN ('Boxe', 'Alongamento')
ON CONFLICT (modalidade_id, nome) DO NOTHING;

INSERT INTO planos (modalidade_id, nome, valor_mensal)
SELECT modalidade.id, 'Mensal', dados.valor_mensal
FROM (VALUES ('Boxe', 140.00), ('Alongamento', 95.00)) AS dados(nome, valor_mensal)
JOIN modalidades modalidade ON modalidade.nome = dados.nome
ON CONFLICT (modalidade_id, nome) DO NOTHING;

WITH dados (nome, email, cidade, modalidade, dia_vencimento) AS (
    VALUES
        ('Ana Ribeiro', 'ana.ribeiro@example.com', 'São Paulo', 'Boxe', 5),
        ('Bruno Martins', 'bruno.martins@example.com', 'São Paulo', 'Alongamento', 10),
        ('Carla Souza', 'carla.souza@example.com', 'Santo André', 'Boxe', 15)
),
alunos_inseridos AS (
    INSERT INTO alunos (nome, email, cidade)
    SELECT nome, email, cidade
    FROM dados
    RETURNING id, email
),
matriculas_inseridas AS (
    INSERT INTO matriculas (aluno_id, data_matricula, dia_vencimento)
    SELECT aluno.id, (CURRENT_DATE - INTERVAL '90 days')::date, dados.dia_vencimento
    FROM alunos_inseridos aluno
    JOIN dados ON dados.email = aluno.email
    RETURNING id, aluno_id
),
vinculos_inseridos AS (
    INSERT INTO matricula_modalidades (matricula_id, modalidade_id, graduacao_id, plano_id, data_inicio)
    SELECT matricula.id, modalidade.id, graduacao.id, plano.id,
           (CURRENT_DATE - INTERVAL '90 days')::date
    FROM matriculas_inseridas matricula
    JOIN alunos_inseridos aluno ON aluno.id = matricula.aluno_id
    JOIN dados ON dados.email = aluno.email
    JOIN modalidades modalidade ON modalidade.nome = dados.modalidade
    JOIN graduacoes graduacao ON graduacao.modalidade_id = modalidade.id AND graduacao.nome = 'Iniciante'
    JOIN planos plano ON plano.modalidade_id = modalidade.id AND plano.nome = 'Mensal'
    RETURNING matricula_id, plano_id
)
INSERT INTO fatura_matriculas (matricula_id, data_vencimento, valor, data_pagamento, status)
SELECT vinculo.matricula_id,
       CURRENT_DATE + fatura.dias_ate_vencimento,
       plano.valor_mensal,
       CASE WHEN fatura.status = 'PAGA' THEN CURRENT_DATE + fatura.dias_ate_vencimento END,
       fatura.status
FROM vinculos_inseridos vinculo
JOIN planos plano ON plano.id = vinculo.plano_id
CROSS JOIN (VALUES (-60, 'PAGA'), (-30, 'VENCIDA'), (5, 'ABERTA')) AS fatura(dias_ate_vencimento, status);
