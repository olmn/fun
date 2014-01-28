
// https://docs.google.com/document/d/1cqnoLc2S-VOEjhpH8eslPeKClUMXDd4FuTtLuRkBalY/edit
// http://arduino.cc/en/Reference/attachInterrupt
// https://www.sparkfun.com/products/9320

// if you want the serial for led debug
// 0 -> 1288 bytes
// 1 -> 3090 bytes
#define LED_DEBUG_SERIAL 1

// led pin def
#define LED_BLUE  11
#define LED_RED   10
#define LED_GREEN  9
#define LED_WHITE  6

// direction pin def
#define DIR_UP    3
#define DIR_DOWN  2

// "how sensitive" is the color change to the trackball motion
#define VERT_INC  5

// keeps track of how "up or down" the ball has scrolled
volatile byte verticalPos;

// called when the trackball is scrolling up
void isrUp( void ) {
  // keep vertialPos from overflowing
  int temp = verticalPos + VERT_INC;
  if(temp > 255) temp = 255;
  verticalPos = temp;
 
  #if(LED_DEBUG_SERIAL)
    Serial.print("isrUp  :");
    Serial.println(verticalPos, DEC);
  #endif
}

// called when the trackball is scrolling down
void isrDown( void ) {
  // keep verticalPos from underflowing
  int temp = verticalPos - VERT_INC;
  if(temp < 0) temp = 0;
  verticalPos = temp;
 
  #if(LED_DEBUG_SERIAL)
    Serial.print("isrDown:");
    Serial.println(verticalPos, DEC);
  #endif
}

void setup()  {
  #if(LED_DEBUG_SERIAL)
    Serial.begin(9600);	// opens serial port
  #endif
 
  // attachInterrupt(interrupt, function, mode)
  attachInterrupt(0, isrUp, CHANGE);
  attachInterrupt(1, isrDown, CHANGE);
}

void loop()  {
  // write out to a LED
  analogWrite(LED_GREEN, verticalPos);
 
  // write out the opposite to the other LED
  analogWrite(LED_BLUE, ~verticalPos);
}

