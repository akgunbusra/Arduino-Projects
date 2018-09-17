#include <ISALiquidCrystal.h> //including library
ISALiquidCrystal lcd;
long minute, second;
void setup() {
  lcd.begin();
}
void loop() {
  minute = millis();
  second = millis();
  long temp = minute / 60000;
  if (temp < 10) {
    lcd.print(0);
    lcd.print(temp);
  }
  else {
    lcd.print(temp);
  }
  lcd.print(":");
  long temp2 = second / 1000;
  temp2 = temp2 % 60;
  if (temp2 < 10) {
    lcd.print(0);
    lcd.print(temp2);
  }
  else if (10 <= temp2 <= 60) {
    lcd.print(temp2);
  }
  delay(1000); //1sn
  lcd.clear();
}
