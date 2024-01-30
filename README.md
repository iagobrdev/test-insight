
# Insight Informática - Teste Lógico + Full Stack

Esse é meu teste lógico para a vaga de programador Full Stack Java / Web na empresa [Insight Informática](http://insightinfo.tecnologia.ws)

## Tecnologias

- Backend
	- Java (jdk-18.0.2.1)
	 - JPS
	 - Servelts
	 - Apache TomCat 8.5.98 (Usuário: admin ; Senha: admin)

- Frontend
	 - HTML
	 - CSS
	 - Bootstrap
	 - JavaScript
	 - jQuery

 
 O teste foi divido em dois Menus (Cadastros e Relatórios) para facilitar a manipulação por parte dos analistas. 

### Cadastros
Nesse menu o usuário irá cadastrar os horários de trabalho e as marcações desejadas.
![](/assets/cadastros.png "Menu Cadastros")

### Relatórios
Aqui será aplicada a lógica com base nos horários cadastrados e nas marcações feitas. O usuário poderá saber se houve Atraso ou se foram feitas Horas Extras.
![](/assets/relatorios.png "Menu Relatórios")

## Especificações do Teste
Faça uma tela com os seguintes componentes:
 
1 – Criar uma tabela que liste “Horário de trabalho”, com dois campos tipo caracter de tamanho 5 e formato HH:MM, chamados entrada e saída, que pode receber até 3 registros.
Coloque os componentes necessários para se cadastrar entrada e saída, (edits, buttons, dataTable e etc) e visualizá-los na tela.   

2 - Criar uma tabela que lista “Marcações Feitas”, com dois campos tipo caracter de tamanho 5 e formato HH:MM, chamados entrada e saída, que pode receber até n registros.

Coloque os componentes necessários para se cadastrar entrada e saída, (edits, buttons, dataTable e etc) e visualizá-los na tela.

3 – Tabela que liste os cálculos de “Atraso” com os componentes necessários para se visualizar seus registros na tela, com a mesma estrutura das demais tabelas.

4 - Tabela que liste os cálculos de “Hora extra” com os componentes necessários para se visualizar seus registros na tela, com a mesma estrutura dos demais Tabelas.
 

Crie uma função de subtração entre os registros as tabelas do padrão entrada e saída.

       
Exemplo 1:

	Tabela-Horario De Trabalho:
			registros:
					08:00  12:00

	Tabela-Marcacoes Feitas:
			registros:
					07:00 11:00

	Se chamarmos a função de subtração passando como parâmetro 1 os registros da Tabela-Horario De Trabalho e 2 os registros da Tabela-Marcacoes Feitas, a função tem que retornar os períodos da Tabela 1 menos o da tabela 2.

	Seria assim:
  
	das 08:00 às 12:00 eu devo tirar das 07:00 às 11:00, portanto fica no primeiro período 11:00 às 12:00

	Vamos analisar, 11:00 às 12:00 seria o período que o funcionário não cumpriu, então seria o atraso           

Exemplo 2:

	Tabela-Horario De Trabalho:
			registros:
					08:00 12:00

	Tabela-Marcacoes Feitas:
			registros:
					07:00 11:00

	Se chamarmos a função de subtração passando como parâmetro 1 os registros da Tabela-Marcacoes Feitas e o 2 os registros da Tabela-Horario De Trabalho, a função tem que retornar o período da Tabela 1 menos o do 2.


	Seria assim:
	das 07:00 às 11:00 eu devo tirar das 08:00 às 12:00, portanto fica no primeiro período 07:00 às 08:00

	Vamos analisar, 07:00 às 08:00 seria o período que o funcionário trabalhou fora do horário dele, então seria o hora extra.

	Esses dois exemplos foram de 1 registro de marcações e 1 registro de horários, mas pode-se ter n registros de marcações e até 3 registro de horários e a função tem que atender.

Exemplo 3:

	Tabela-Horario De Trabalho:

			08:00 12:00
			13:30 17:30

	Exemplos em marcações e o resultado esperado:

	- Marcações:
			06:00 20:00

					Atraso
					   -

					Hora extra
					   06:00 08:00
					   12:00 13:30
					   17:30 20:00
	- Marcações
			07:00 12:30
			14:00 17:00

					Atrasos:
					   13:30 14:00
					   17:00 17:30

					Hora extra:
					   07:00 08:00
					   12:00 12:30

Exemplo 4:

	Tabela-Horario De Trabalho:

			22:00 05:00

	Exemplos em marcações e o resultado esperado:

	- Marcações:
			21:00 04:00

					Atraso
					   04:00 05:00

					Hora extra
					   21:00 22:00

	- Marcações
			03:00 07:00

					Atrasos:
					   22:00 03:00

					Hora extra:
					   05:00 07:00# Insight Informática - Teste Lógico + Full Stack

Esse é meu teste lógico para a vaga de programador Full Stack Java / Web na empresa Insight Informática.

Tecnologias Utilizadas

* Backend
 - Java
 - JPS
 - Servelts

* Frontend
 - HTML
 - CSS
 - Bootstrap
 - JavaScript
 - jQuery
 

Faça uma tela com os seguintes componentes:
 
1 – Criar uma tabela que liste “Horário de trabalho”, com dois campos tipo caracter de tamanho 5 e formato HH:MM, chamados entrada e saída, que pode receber até 3 registros.
Coloque os componentes necessários para se cadastrar entrada e saída, (edits, buttons, dataTable e etc) e visualizá-los na tela.   

2 - Criar uma tabela que lista “Marcações Feitas”, com dois campos tipo caracter de tamanho 5 e formato HH:MM, chamados entrada e saída, que pode receber até n registros.

Coloque os componentes necessários para se cadastrar entrada e saída, (edits, buttons, dataTable e etc) e visualizá-los na tela.

3 – Tabela que liste os cálculos de “Atraso” com os componentes necessários para se visualizar seus registros na tela, com a mesma estrutura das demais tabelas.

4 - Tabela que liste os cálculos de “Hora extra” com os componentes necessários para se visualizar seus registros na tela, com a mesma estrutura dos demais Tabelas.
 

Crie uma função de subtração entre os registros as tabelas do padrão entrada e saída.

       
Exemplo 1:

	Tabela-Horario De Trabalho:
			registros:
					08:00  12:00

	Tabela-Marcacoes Feitas:
			registros:
					07:00 11:00

	Se chamarmos a função de subtração passando como parâmetro 1 os registros da Tabela-Horario De Trabalho e 2 os registros da Tabela-Marcacoes Feitas, a função tem que retornar os períodos da Tabela 1 menos o da tabela 2.

	Seria assim:
  
	das 08:00 às 12:00 eu devo tirar das 07:00 às 11:00, portanto fica no primeiro período 11:00 às 12:00

	Vamos analisar, 11:00 às 12:00 seria o período que o funcionário não cumpriu, então seria o atraso           

Exemplo 2:

	Tabela-Horario De Trabalho:
			registros:
					08:00 12:00

	Tabela-Marcacoes Feitas:
			registros:
					07:00 11:00

	Se chamarmos a função de subtração passando como parâmetro 1 os registros da Tabela-Marcacoes Feitas e o 2 os registros da Tabela-Horario De Trabalho, a função tem que retornar o período da Tabela 1 menos o do 2.


	Seria assim:
	das 07:00 às 11:00 eu devo tirar das 08:00 às 12:00, portanto fica no primeiro período 07:00 às 08:00

	Vamos analisar, 07:00 às 08:00 seria o período que o funcionário trabalhou fora do horário dele, então seria o hora extra.

	Esses dois exemplos foram de 1 registro de marcações e 1 registro de horários, mas pode-se ter n registros de marcações e até 3 registro de horários e a função tem que atender.

Exemplo 3:

	Tabela-Horario De Trabalho:

			08:00 12:00
			13:30 17:30

	Exemplos em marcações e o resultado esperado:

	- Marcações:
			06:00 20:00

					Atraso
					   -

					Hora extra
					   06:00 08:00
					   12:00 13:30
					   17:30 20:00
	- Marcações
			07:00 12:30
			14:00 17:00

					Atrasos:
					   13:30 14:00
					   17:00 17:30

					Hora extra:
					   07:00 08:00
					   12:00 12:30

Exemplo 4:

	Tabela-Horario De Trabalho:

			22:00 05:00

	Exemplos em marcações e o resultado esperado:

	- Marcações:
			21:00 04:00

					Atraso
					   04:00 05:00

					Hora extra
					   21:00 22:00

	- Marcações
			03:00 07:00

					Atrasos:
					   22:00 03:00

					Hora extra:
					   05:00 07:00