#include <ISADefinitions.h> 
 void setup(){  

  for(int i = 0; i <8 ; i++){
    pinMode(LEDS[i], OUTPUT);
  }
  //digitalWrite(LED1,LOW);
  } 
 void loop(){
 int counter =8;
 for(int i =0; i<counter; i++){
  digitalWrite(LEDS[i], HIGH);
  delay(1000);
  digitalWrite(LEDS[i],LOW);
  delay(1000);
 }  
for(int i=7; i>=0; i--)
{
  digitalWrite(LEDS[i], HIGH);
  delay(1000);
  digitalWrite(LEDS[i],LOW);
  delay(1000);
  }      

}  
