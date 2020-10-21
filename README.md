## Uav-Traffic-Management

#### This project is an implementation of the paper 「[Predicting Flight Stability of UAV Flight Plan Based on Deep Learning](https://ndltd.ncl.edu.tw/cgi-bin/gs32/gsweb.cgi/ccd=3esI3R/record?r1=1&h1=1)」

 * This service provides real drone management services. The experimental drone sends the real flight trajectory data of the drone back to the server through LoRa. Then predict the flight trajectory of the drone through the LSTM model([model project](https://github.com/ShawnSWu/Predict-Trajectory-LSTM-Model)), and then analyze the flight stability of the trajectory. 

**The system uses Spring boot Framework as the main framework of the Web Server. The database uses a connected database MySQL 5.7 to store flight plan trajectories, pilots, flight trajectories and other information through JSON Web Tokens (JWT), and is used as a verification mechanism for system access, and runs on Docker containers. [Website](http://utm-system-frontend.herokuapp.com/realtime_map/html/drone-map.html)**

===
![](https://i.imgur.com/V6zrzko.png)
