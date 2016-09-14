#!/bin/bash
cd /root/citiCup/javaServer/
if [ -f "nohup.out" ]; then  
  rm -f nohup.out 
fi 

if [ -f "pid" ]; then  
  kill $(cat pid)  
fi 
nohup java -Xmx400m -Dfile.encoding=UTF-8 -jar ./nju.citicup-0.0.1-SNAPSHOT.jar &
echo "$!" > pid


cd /root/citiCup/pythonServer/
if [ -f "nohup.out" ]; then  
  rm -f nohup.out 
fi 

if [ -f "pid" ]; then  
  kill $(cat pid)  
fi 
nohup python flaskTest.py prod &
echo "$!" > pid