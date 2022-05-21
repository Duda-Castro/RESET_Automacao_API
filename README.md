# RESET_Automacao_API
teste

Melhoria: Documentação solicita retorno 201 (created) uma melhoria seria retornar 200 (ok). 
Bug (Documentação): Documentação solicita que o .header() de validação dos métodos PUT, PATCH e DELETE
seja colocado "Authorisation" enquanto o correto é "Authorization".
Bug (Documentação): Documentação solicita que o .header() de validação dos métodos PUT, PATCH e DELETE
seja colocado "Authorisation" enquanto o correto é "Authorization".

Bug(POST): Cadastro com payload contendo info extra é aceito.
Bug(POST): Cadastro de reserva com datas no passado é aceito.
Bug(POST): Criação de reserva não solicita token.
Bug(PUT): Alteração de reserva inexistente gera 405 NotAllowed e não 404 notfound.
Bug(GET): Busca com filtro checkin está retornando apenas datas posteriores, ignorando o próprio dia informado no filtro(3).



