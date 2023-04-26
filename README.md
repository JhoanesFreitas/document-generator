# fake-document-generator

A fake-document-generator é uma ferramenta que busca ajudar os desenvolvedores a testar os seus
softwares quando necessitam de números de CPFs, CNPJs ou RGs.

# configuração

De início, acrescente o seguinte trecho no root <b>build.gradle</b>

```
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```

Em seguida, você deve adicionar a dependência do projeto no arquivo gradle do módulo desejado

```
dependencies {
    implementation 'com.github.JhoanesFreitas:fake-document-generator:v1.1.2'
}
```

# Uso

Você pode usar três classes para gerar os números. São elas:

* CpfGenerator
* CnpjGenerator
* RgGenerator

Por exemplo, você pode querer obter um número de CPF relacionado ao estado do Rio Grande do Norte. 
Assim, você faria:

```
val cpfGenerator = CpfGenerator.Builder()
    .setFederationUnit(FederationUnit.RN)
    .build()
    
val cpf = cpfGenerator.generateCpf()
```

O método `setFederationUnit` é opcional. Caso deseje um CPF não importando o Estado relacionado,
você pode desconsiderar a chamada do método. Além disso, ele é uma extensão da classe Builder. Portanto, 
importe-o quando solicitado.

Nota: A geração de RG é baseada nas regras da SSP/SP.

Além disso, caso queira obter o número do documento com máscara,
basta chamar o método `withSymbols` passando `true` como parâmetro. 
Assim, o CPF (por exemplo) virá no modelo `###.###.###-##`.

Há outros métodos opcionais, são eles: `setPrefix` e `setSuffix`.

Por fim, você poderá chamar o método `generateCpfSet(quantity: Int)` para obter um conjunto de
CPFs. Esse é um método `suspend`, então necessitará ser chamado dentro de um contexto de Coroutine.
