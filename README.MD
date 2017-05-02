# Xy Inc
XY Inc, salve pontos de interesse (PoI´s) e os encontre de forma facil, listando todos os pontos existentes ou aqueles que estão em um raio de dez metros a partir de sua localização atual.

#####  

# EndPoints disponíveis
   A api Rest utilza a formatação Json para comunicação e disponibiliza os seguintes end points abaixo
##### Requisição/ Resposta
    Caminho     Método    Função
    /place      Get       retorna todos os POI's
    /place/x,y  Get       retorna PoI´s num raio de dez metros a partir de xy 
    /place      Post      salva um PoI
    /error                apresenta a excessão lançada pelo servidor
    
##### Códigos de resposta
   O caminho /palce/x,y retorna bad request caso x,y não correspondam ao padrão
   "[0-9]+(\.[0-9]+)?,[0-9]+(\.[0-9]+)?" . Exemplo, localhost:8080/place/20.2,aa irá acusar uma má requisição(404). Além disso um json é retornado informando o motivo da má requisição.  

    Método Caminho         Código                        
    Get    /place          200(Ok) ou 204(No content)
    Get    /place/x,y      200(Ok), 204(No content), 400(Bad Request)     
    Post   /plcae          201(Created) 
           /error          500(Internal server error)
# Como rodar
### Aplicação
  - Beixe o projeto
  - Na pasta raiz do projeto
  - Execute o comando ```gradle bootrun``` para subir a aplicação

### Testes
  - Na pasta raiz do projeto
  - Execute o comando ```gradle test```
