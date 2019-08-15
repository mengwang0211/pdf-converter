
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.github.mengwang0211/pdf-converter/badge.svg)](https://maven-badges.herokuapp.com/maven-central/io.github.mengwang0211/pdf-converter/)
[![License](https://img.shields.io/badge/license-Apache%202-4EB1BA.svg)](https://www.apache.org/licenses/LICENSE-2.0.html)


### pdf-converter

#### 使用方法

```html

    <groupId>io.github.mengwang0211</groupId>
    <artifactId>pdf-converter</artifactId>
    <version>1.0.0</version>

```

##### freemarker 转 pdf
```java

       // 填入freemarker模板的文件路径
        URL url = Thread.currentThread().getContextClassLoader().getResource("");

        // 数据填充
        Map initData = initData();

        // 返回生成的pdf路径
        String pdfPath = FreeMarkToPdf.convert(url.getPath(),"template.ftl",initData);

```