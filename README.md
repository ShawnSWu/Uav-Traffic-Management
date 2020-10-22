## Uav-Traffic-Management

#### This project is an implementation of the paper 「[Predicting Flight Stability of UAV Flight Plan Based on Deep Learning](https://ndltd.ncl.edu.tw/cgi-bin/gs32/gsweb.cgi/ccd=3esI3R/record?r1=1&h1=1)」

>This service provides real drone management services(hereinafter referred to as "UTM"). In this experiment, The drone will send the real flight trajectory data of the drone back to the UTM through LoRa. When UTM receives trajectory data, the service predicts the flight trajectory of the drone through the LSTM model([model project](https://github.com/ShawnSWu/Predict-Trajectory-LSTM-Model)), and then analyze the flight stability of the trajectory to achieve the purpose of monitoring drone flight, when flight stability too low, UTM will warn the pilot, recommend the pilot terminate the drone flight.


The system uses Spring boot Framework as the main framework of the Web Server.The service provides flight plan management, real-time monitoring of flight trajectories and flight stability analysis, and runs on Docker containers. 



**[Website](http://utm-system-frontend.herokuapp.com/realtime_map/html/drone-map.html)<br>
Test Account:ShawnWu, Password:password**

---
### Thesis architecture
![](https://i.imgur.com/V6zrzko.png)
