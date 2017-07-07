JSON2XMLConverter
------------------
This application will help to translate the JSON file into xml file


Prerequisites
-------------
JDK1.7+
Apache Ant (http://ant.apache.org/bindownload.cgi)


Installation and Configuration
------------------------------
Installing Ant on Linux
-> Enter the URL: http://ant.apache.org/bindownload.cgi
-> On the Apache Ant Project page, find the heading Latest Release of Ant.
-> Select apache-ant-1.10.1-bin.tar.gz [PGP] [SHA1] [SHA512] [MD5].
-> Save and extract the package file into a Linux home directory.
-> Set the ANT_HOME environment variable to the directory where you installed Ant: export ANT_HOME=${ant_dir}.
-> Set the PATH environment variable to include the directory where you installed the Ant bin directory: export PATH=${ANT_HOME}/bin:${JAVA_HOME}/bin:${PATH}.


Building Application
--------------------
Go to source root folder.
Run command : ant compile


Packaging Application
---------------------
Go to source root folder.
Run command : ant package


Using Application
-----------------
Go to source root folder.
Run command : ant execute -Djson=jsonfilepath -Dxml=xmlfilepath
