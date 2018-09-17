#include <ISADefinitions.h>
#include <ISALiquidCrystal.h>


ISALiquidCrystal lcd;

void setup() {
  // put your setup code here, to run once:
  lcd.begin();
  lcd.print( "HELLO");
  //lcd.clear();
 
}

void loop() {
  // put your main code here, to run repeatedly:

}
