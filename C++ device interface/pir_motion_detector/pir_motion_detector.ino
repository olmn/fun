// http://bildr.org/2011/06/pir_arduino/
// https://www.sparkfun.com/products/8630

int pirPin = 2; //digital 2

void setup(){
 Serial.begin(9600); 
 pinMode(pirPin, INPUT);
}


void loop(){
  int pirVal = digitalRead(pirPin);

  if(pirVal == LOW){ //was motion detected
    Serial.println(">Motion Detected"); 
  }
  else
  {
    Serial.println(">"); 
  }
    
  delay(500); 

}
