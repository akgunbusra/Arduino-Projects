 void setup() {
  //Initialize serial and wait for port to open:
  Serial.begin(9600);

  // prints title with ending line break
  Serial.println("ASCII Table ~ Character Map");
}

void loop(){
  
  String test = Serial.readString();

    Serial.println(test);

  }
