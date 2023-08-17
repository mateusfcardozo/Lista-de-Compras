Lista_de_Compras_finalizado

View_Login:

No view_login e onde o usuario faz login para entrar para o View_Home, 
pode ir para a parte de cadastrar-se ou acabou esquecendo a senha.

No cadastro ele vai te levar pro View_Cadastro.
No esqueci senha tem uma verificação de email ou celular para ver se existe no db,
depois gera um numero para o usuario colocar o numero gerado,se for igual o numero gerado ira para a parte de alterar a senha,
senao da uma mensagem e fecha.

Para entrar com o login vai ter uma verificação no email e senha e verificar se existem e são iguais aos dados informados.


View_Cadastro:
No View_Cadastro tem duas opções, a de cadastrar-se ou fazer login.

quando o usuario ira se cadastrar ele ira fazer verificações se o email tem '@', 
o celular tem 11 caracteres, a senha são iguais ou se tem dados
vazios(celular não e obrigatorio).


View_Home:
No View_Home tem 4 panel para o usuario acessar 'BackgroundConteudo','TodasListas','AdicionarLista' e 'InformaçõesConta'.

BackgroundConteudo: e onde fica os botões 'Listas','AdicionarListas','Conta' e 'sair'.

TodasListas:no TodasListas aparece as listas criadas pelo usuario, pela ações pode remover a lista ou modificar ela.

AdicionarLista: no AdicionarLista e onde se pode criar uma lista e adicionar itens dentro dela,
e nesse mesmo lugar tambem pode modificar os itens da lista.

InformaçõesConta: aqui aparecer os dados do usuario(nome, celular, email) e se pode alterar os dados ou
 ate mesmo deletar a conta ou os dados(listas criadas e seus itens).