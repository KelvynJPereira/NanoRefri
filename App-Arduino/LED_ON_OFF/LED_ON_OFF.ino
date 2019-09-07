void setup()
{
  Serial.begin(9600);
  pinMode(9,OUTPUT);
  pinMode(10,OUTPUT);
}

void loop() 
{
  char data;
  if(Serial.available())
  {
    data=Serial.read();

    switch(data)
    {
      case 'A':
        digitalWrite(9,HIGH);
      break;
      
      case 'B':
        digitalWrite(9,LOW);
      break;
      
      case '1':
        digitalWrite(10,HIGH);
      break;
      
      case '2':
        digitalWrite(10,LOW);
      break;
      
      case 'V':
        digitalWrite(9,HIGH);
        digitalWrite(10,HIGH);
      break;
      
      case 'F':
        digitalWrite(9,LOW);
        digitalWrite(10,LOW);
      break;
    }
    
  }
  
}
