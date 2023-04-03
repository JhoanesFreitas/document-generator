# fake-document-generator

A fake-document-generator é uma ferramenta que busca ajudar os desenvolvedores a testar os seus
softwares quando necessitam de números de CPFs, CNPJs ou RGs.

# congifuração

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
    implementation 'com.github.JhoanesFreitas:fake-document-generator:v1.1.0'
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
você pode desconsiderar a chamada do método.

Há outros métodos opcionais, são eles: `setPrefix` e `setSuffix`.

Por fim, você poderá chamar o método `generateCpfSet(quantity: Int)` para obter um conjunto de
CPFs. Esse é um método `suspend`, então necessitará ser chamado dentro de um contexto de Coroutine.
