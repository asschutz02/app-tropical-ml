O SISTEMA:
  - 
  - Gerenciador de preços de produtos anunciados por clientes da tropical no Mercado Livre, onde produtos anunciados abaixo do PMS (Preço Mínimo Sugerido), são incluidos no relatório e enviados por email
  - Java 11

---------
## 1. Deploy

- Feito no Heroku
- Banco de dados PostgreSQL
---------
## 2. Controller
- LojistaController: Gerenciamento do lojista (cliente da tropical)
- NicknameController: Gerenciamento do nickname (nickname do lojista)
- NicknameEmailController: Gera um relatório com os nicknames cadastrados
- ProductController: Gerenciamento do produto (produtos monitorados pela tropical)
  - alguns produtos tem "%" no nome, e isso quebra a URL, então no frontend é substituido "%" por "!" e no backend é feito a conversão novamente de "!" para "%" 
- ProductEmailController: Gera um relatório com os produtos cadastrados
- RelatorioController: Gera o relatório dos produtos selecionados
- SearchController: *DESCONTINUADO* -> Seria para pesquisar qualquer produto fornecendo um preço para comparação (ainda utiliza Selenium, a v1 do sistema)
- SellerController: Gerenciamento do vendedor da Tropical
---------
## 3. EmailJavaSender
- Classe utilizada para enviar a planilha do relatório por email
---------
## 4. Pacote Entity 
- Pacote com as classes que representam as tabelas do banco de dados
---------
## 5. Pacote Excel
- ExcelHelper: Escreve as linhas do excel para o relatório
- ExcelNickname: Gera o excel para o nickname
- ExcelProduct: Gera o excel para o produto
- ExcelExecuter: Cria o excel para o relatório
---------
## 6. Pacote Mapper
- Interfaces onde as queries para o banco são escritas
---------
## 7. Pacote MercadoLivre
- client: onde a integração com o Mercado Livre é feita
- mapper: onde a resposta do Mercado Livre é mapeada para uma classe do projeto
- model: classes que mapeiam a resposta do Mercado Livre
- service: classe utilitária onde as regras são aplicadas
  - desconsidera produtos usados
  - desconsidera produtos similares
  - verifica se a marca é Tropical ou OceanTech
  - exclui produtos de outras marcas
  - valida o PMS (estando abaixo, o produto entra no relatório)
---------
## 8. Service
- pacote dos serviços das entidades
  - quando implementado, eu não estabeleci nenhum relacionamento entre as tabelas, então os updates são feitos manualmente, e os elementos em comum nas tabelas são duplicados, ao inves de serem gerenciados por PK e FK
---------
## 9. Procfile
- arquivo usado pelo Heroku para fazer o deploy



