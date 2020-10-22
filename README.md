## Uav-Traffic-Management

#### This project is an implementation of the paper 「[Predicting Flight Stability of UAV Flight Plan Based on Deep Learning](https://ndltd.ncl.edu.tw/cgi-bin/gs32/gsweb.cgi/ccd=3esI3R/record?r1=1&h1=1)」

 * This service provides real drone management services. In this experiment drone sends the real flight trajectory data of the drone back to the server through LoRa. Then predict the flight trajectory of the drone through the LSTM model([model project](https://github.com/ShawnSWu/Predict-Trajectory-LSTM-Model)), and then analyze the flight stability of the trajectory. 

#### **The system uses Spring boot Framework as the main framework of the Web Server.The service provides flight plan management, real-time monitoring of flight trajectories and flight stability analysis, and runs on Docker containers. 
===

[Website](http://utm-system-frontend.herokuapp.com/realtime_map/html/drone-map.html)**
Test account:ShawnWu , password:password

---
### Thesis architecture
![](https://i.imgur.com/V6zrzko.png)
