# Install
## 一、Basic environment installation
1. jdk 1.8.0_151
2. git 2.14.3
3. maven 3.3.9
4. mysql 5.6.39

Warning: Add the above command to the global environment path!!!
## 二、Download source code
`git clone https://github.com/XDUPSMC3Team/ProjectBackend.git`
## 三、Configuration database environment
1. Import the bookstore.sql file under `src/main/resources` into the local mysql server。
2. Modify the value of `jdbc.url` in `db.properties` under `src/main/resources`
`jdbc:mysql://localhost:3306/shopping?&useUnicode=true&characterEncoding=UTF8&useSSL=false`
## 四、Server deployment & run (require networking)
0. Open the console and go to the project root directory，which is `/ProjectBackend`.
1. input `mvn install`,Installation dependence.
2. input `mvn package`,package this project.
3. Go into the target directory，input `java -jar {the name of project jar}.jar`,Do not close the console.
## 五、Visiting
Open a browser, Visit `localhost:8080`.
# ProjectBackend
## 1. LocalDateTime 与  sql 的映射问题
https://blog.csdn.net/mn960mn/article/details/53141366
## 2. 邮件发送问题
https://blog.csdn.net/u013938484/article/details/51939587
https://blog.csdn.net/lovelichao12/article/details/80097571
## 3. 对于图片保存问题
https://blog.csdn.net/zahngjialiang/article/details/78614739

# Frontend
# ParkNshop_C3_Frontend_README

## Config Environment

we should install nodejs & npm at first(if we are suppose to develop the frontend of this project).

### install nodejs & npm

nodejs source code are here: https://nodejs.org/en/download/

1. Choose the suitable version（base on your OS）
![](http://mweb.helloyzy.cn/15466982403375.jpg)
We choose Windows as example:
![](http://mweb.helloyzy.cn/15466983202093.png)
![](http://mweb.helloyzy.cn/15466983237745.png)
![](http://mweb.helloyzy.cn/15466983261951.png)
![](http://mweb.helloyzy.cn/15466983292561.png)
![](http://mweb.helloyzy.cn/15466983316282.png)
![](http://mweb.helloyzy.cn/15466983344889.png)
![](http://mweb.helloyzy.cn/15466983375342.png)

If you install Nodejs success,you can operate like this:
![](http://mweb.helloyzy.cn/15466983399151.png)

## develop project

##### step1:

> git clone https://github.com/XDUPSMC3Team/ProjectFrontend.git


##### step2

> cd ./ProjectFrontend/dmall


##### step3

> npm install 

##### step4

> npm run theme:make


##### step5

> npm run dev

## build project

enter the project directory

##### step1:

> git clone https://github.com/XDUPSMC3Team/ProjectFrontend.git


##### step2

> cd ./ProjectFrontend/dmall


##### step3

> npm install 

##### step4

> npm run theme:make

##### step5

> npm run build && npm run serve