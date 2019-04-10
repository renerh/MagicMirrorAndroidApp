import sys
from time import sleep

import signal
import gpiozero
from gpiozero import LEDBoard, Button
from threading import Thread
import firebase_admin
from firebase_admin import credentials
from firebase_admin import db


LED = LEDBoard(red=26, green=13, blue=19, desk=17)
#RELAY_PIN = 17 #
#relay = gpiozero.OutputDevice(RELAY_PIN, active_high=False, initial_value=False)
#

PAHT_CRED = '/home/pi/Desktop/IOT/firebaseKey.json'
URL_DB = 'https://fir-3-4ce17.firebaseio.com/'

REF_HOME = 'home'
REF_LUCES = 'luces'
REF_BOTONES = 'botones'
REF_RED_LIGHTS = 'redLights'
REF_BLUE_LIGHTS = 'blueLights'
REF_GREEN_LIGHTS = 'greenLights'
REF_DESK_LIGHTS ='deskLights'

class IOT():


    def __init__(self):

        cred = credentials.Certificate(PAHT_CRED)
        firebase_admin.initialize_app(cred, {
            'databaseURL': URL_DB
        })

        self.refHome = db.reference(REF_HOME)

        self.estructuraInicialDB() # solo ejecutar la primera vez

        self.refLuces = self.refHome.child(REF_LUCES)

        self.refRedLights = self.refLuces.child(REF_RED_LIGHTS)
        self.refGreenLights = self.refLuces.child(REF_GREEN_LIGHTS)
        self.refBlueLights = self.refLuces.child(REF_BLUE_LIGHTS)
        self.refDeskLights = self.refLuces.child(REF_DESK_LIGHTS)
        
    def estructuraInicialDB(self):

        self.refHome.set({

            'luces': {

                'redLights':False,

                'greenLights': False,

                'deskLights': True,

                'blueLights':False

            }

        })

   

    def ledControlGPIORed(self, estado):
        if estado:
            LED.red.on()
            print('LED ON')

        else:
            LED.red.off()
            print('LED OFF')


    def lucesStartRed(self):
        E, i = [], 0

        estado_anterior = self.refRedLights.get()
        self.ledControlGPIORed(estado_anterior)

        E.append(estado_anterior)


        while True:
          estado_actual = self.refRedLights.get()
          E.append(estado_actual)


          if E[i] != E[-1]:
              self.ledControlGPIORed(estado_actual)

          del E[0]
          i = i + i
          sleep(0.4)

    def ledControlGPIOBlue(self, estado):
        if estado:
            LED.blue.on()
            print('LED ON')

        else:
            LED.blue.off()
            print('LED OFF')



    def lucesStartBlue(self):

        E, i = [], 0

        estado_anterior = self.refBlueLights.get()
        self.ledControlGPIOBlue(estado_anterior)

        E.append(estado_anterior)


        while True:
          estado_actual = self.refBlueLights.get()
          E.append(estado_actual)


          if E[i] != E[-1]:
              self.ledControlGPIOBlue(estado_actual)


          del E[0]
          i = i + i
          sleep(0.4)

    def ledControlGPIOGreen(self, estado):
        if estado:
            LED.green.on()
            print('LED ON')

        else:
            LED.green.off()
            print('LED OFF')



    def lucesStartGreen(self):

        E, i = [], 0

        estado_anterior = self.refGreenLights.get()
        self.ledControlGPIOGreen(estado_anterior)

        E.append(estado_anterior)


        while True:
          estado_actual = self.refGreenLights.get()
          E.append(estado_actual)


          if E[i] != E[-1]:
              self.ledControlGPIOGreen(estado_actual)

          del E[0]
          i = i + i
          sleep(0.4)

          

    def ledControlGPIODesk(self, estado):
        if estado:
            LED.desk.on()
            print('LED ON')

        else:
            LED.desk.off()
            print('LED OFF')



    def lucesStartDesk(self):

        E, i = [], 0

        estado_anterior = self.refDeskLights.get()
        self.ledControlGPIODesk(estado_anterior)

        E.append(estado_anterior)


        while True:
          estado_actual = self.refDeskLights.get()
          E.append(estado_actual)


          if E[i] != E[-1]:
              self.ledControlGPIODesk(estado_actual)

          del E[0]
          i = i + i
          sleep(0.4)
    

    


print ('START !')

iot = IOT()


subproceso_ledRed = Thread(target=iot.lucesStartRed)
subproceso_ledRed.daemon = True
subproceso_ledRed.start()

subproceso_ledGreen = Thread(target=iot.lucesStartGreen)
subproceso_ledGreen.daemon = True
subproceso_ledGreen.start()

subproceso_ledBlue = Thread(target=iot.lucesStartBlue)
subproceso_ledBlue.daemon = True
subproceso_ledBlue.start()

subproceso_ledDesk = Thread(target=iot.lucesStartDesk)
subproceso_ledDesk.daemon = True
subproceso_ledDesk.start()

signal.pause()
