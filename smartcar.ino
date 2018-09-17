int enable=4;
int phase=5;
int enable2=6;
int phase2=7;
int mode =8;
int echoPin=10;//forward sensor
int trigPin=9;
int trigPin2=11;//right side sensor
int echoPin2=12;
int trigPin3=13;// left side sensor
int echoPin3=3;
String direct="";
String state="forward";
long distance,duration;
int flag=0;
void setup() {
for(int i=4; i<=9; i++){
  pinMode(i,OUTPUT);
}
pinMode(trigPin2,OUTPUT);
pinMode(trigPin3,OUTPUT);
pinMode(echoPin,INPUT);
pinMode(echoPin2,INPUT);
pinMode(echoPin3,INPUT);
digitalWrite(mode,HIGH);
Serial3.begin(115200);
Serial.begin(9600);
}

void loop() {
  sub();
  set();
//measuringDistance(trigPin3,echoPin3);
if(flag==1){
  
//  Serial3.println(distance);
if(measuringDistance(trigPin3,echoPin3)==1){
  turnLeft();
  delay(520);
  goForward();
}
else if(measuringDistance(trigPin,echoPin)==1){
  goForward();
}
else if(measuringDistance(trigPin2,echoPin2)==1){
  turnRight();
   delay(520);
  goForward();
}
 delay(520);
 stopEngines(); 
 }

}
void sub(){
  direct=Serial3.readString();
  direct=direct.substring(0,direct.length()-2);
  Serial.println(direct);
}
void set(){
  if(direct=="start"){
     turnLeft();
     flag=1;
  }
 if(direct=="stop"){
    stopEngines();
    flag=0;
  }
  if(direct=="backward"){
    gobackward();
  }
}
void goForward(){
  digitalWrite(phase,LOW);
  digitalWrite(enable,HIGH);
  digitalWrite(phase2,HIGH);
  digitalWrite(enable2,LOW);
}
void gobackward(){
  digitalWrite(phase,HIGH);
  digitalWrite(enable,LOW);
  digitalWrite(phase2,LOW);
  digitalWrite(enable2,HIGH);
}
void turnRight(){
  digitalWrite(phase,LOW);
  digitalWrite(enable,HIGH);
  digitalWrite(phase2,LOW);
  digitalWrite(enable2,HIGH);
}
void turnLeft(){
  digitalWrite(phase,HIGH);
  digitalWrite(enable,LOW);
  digitalWrite(phase2,HIGH);
  digitalWrite(enable2,LOW);
}
void stopEngines(){
  digitalWrite(phase,LOW);
  digitalWrite(enable,LOW);
  digitalWrite(phase2,LOW);
  digitalWrite(enable2,LOW);
}
int measuringDistance(int newTrig,int newEcho){
  digitalWrite(newTrig,LOW);
  delay(10);
  digitalWrite(newTrig,HIGH);
  delay(10);
  digitalWrite(newTrig,LOW);
  duration=pulseIn(newEcho,HIGH);
  distance=(duration/2)/29.1;
  //Serial.println(distance);
  Serial3.print(newTrig);
  Serial3.print(" ");
  Serial3.println(distance);
 if(distance>=2 && distance<=45){
    return 0;
  } 
  else{
    return 1;
  }
}

