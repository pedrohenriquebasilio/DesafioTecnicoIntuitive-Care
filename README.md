# AtualizaRolProcedimentos

Este projeto tem como objetivo realizar o processo de scraping no site da ANS (Agência Nacional de Saúde Suplementar) para baixar dois arquivos anexos em formato PDF, compactá-los em um arquivo ZIP e salvá-los em um diretório local. Utiliza a biblioteca Selenium WebDriver para automação do navegador e a biblioteca Java Zip API para compactação dos arquivos.

## Funcionalidades

### 1. Configuração do Robô (Robo de Web Scraping)

A classe `AtualizaRolProcedimentos` configura o ambiente do Selenium WebDriver para interagir com o navegador Chrome, com as seguintes funcionalidades:

- **Configuração do ChromeDriver**: A classe carrega o `chromedriver.exe` necessário para o Selenium controlar o navegador Chrome.
- **Configuração das Preferências de Download**: A configuração define o diretório de download (`downloadPath`) para salvar os arquivos PDF e configura para que o Chrome baixe arquivos sem pedir confirmação.
- **Maximização do Navegador e Desabilitação de Extensões**: O navegador é iniciado maximizado e com as extensões e notificações desabilitadas para uma execução mais fluida e sem interrupções.

### 2. Navegação e Acesso

A função `entraNoSite` realiza o acesso ao site da ANS e executa as seguintes ações:

- **Aceitação de cookies**: Se o botão de aceitação de cookies estiver presente, ele será clicado automaticamente.
- **Baixar arquivos**: Os dois anexos (Anexo I e Anexo II) são baixados ao localizar os links correspondentes no site, através do identificador único (UID).

### 3. Compressão de Arquivos em ZIP

Após o download dos arquivos, a função `comprimirArquivosEmZip` é responsável por compactar todos os arquivos encontrados no diretório de download em um único arquivo ZIP. O arquivo ZIP gerado será armazenado no diretório de download com o nome `anexos_comprimidos.zip`.

### 4. Testes e Validações

A classe realiza verificações durante o processo de download e compactação, garantindo que os arquivos sejam baixados corretamente. O código está estruturado de forma a permitir a fácil detecção de falhas, caso ocorra algum erro nas etapas.

## Dependências

- Selenium WebDriver
- ChromeDriver

## Execução

Para executar o projeto, existem duas maneiras:

#Primeira opção

1. Faça o clone do projeto na sua maquina e sete as variaveis para os caminhos desejados (chromedriver, dowloadpath)
2. execute o metodo indicado na classe main, ele checará o caminho do chromedriver e também o caminho para dowload
3. por padrão, neste repositorio vc já tem os arquivos em suas devidas posições, por isso se não tiver necessidade, apenas execute o projeto. 

#Segunda opção

1. gere o jar do repositorio ou use o disponibilizado na pasta src, execute através do terminal java -jar AtualizaRolproced.jar
2. os arquivos de saida estarão por padrão na pasta resource.

---

## Considerações Finais

Este código foi desenvolvido de forma modular, buscando garantir a robustez do processo de scraping, download e compactação. Os testes e verificações adicionais permitem maior segurança no fluxo de execução.
