1.This is a maven project,you need open it by eclipse with maven plug or convert it to other type project.

2.After you open this project, you need do some configuration. 
open the 'config.properties' at 'src/main/resources' folder,you can see some configuration in it.
2.1
	'file.xml.path' is the path of the file of the xml file.
	'file.xml.path.type' is the file path type,if it not equals the FILE_XML_TYPE_ABSOLUTELY's value in NSNKey.java, we will use the classpath as the path.
	'file.output.brand.path' is the path of the output file which only contain all brand. 
	'file.output.brand.product.path' is the path of the output file which contain all brand and product. 
	'file.output.brand.empty.path' is the path of the output file which contain the product without brand. 
2.2 for the output file,we only support absolutely path,it need include file name.

3.After you finished config, you can run as application in 'src/main/java/com/nsn/test/main/Test.java'.

4.For the console,


