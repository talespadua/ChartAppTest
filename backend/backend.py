from bottle import run, route, request, template, TEMPLATE_PATH
import os
from sys import argv

class Balance():
    def __init__(self):
        self.val1 = 270.0
        self.val2 = 149.0
        self.val3 = 332.0
        self.val4 = 247.0
        self.val5 = 227.0

    def set_new_balance(self, val1, val2, val3, val4, val5):
        self.val1 = val1
        self.val2 = val2
        self.val3 = val3
        self.val4 = val4
        self.val5 = val5

TEMPLATE_PATH.insert(0, os.path.abspath(os.path.join(os.path.dirname(__file__), "views")))


def main():
    balance = Balance()

    @route('/get_balance')
    def get_balance():
        return balance.__dict__

    @route('/')
    def temp():
        return template('forms')

    @route('/update_balance', method='POST')
    def update_balance():
        val1 = request.forms.get('val1')
        if val1 is "":
            val1 = "0"
        val2 = request.forms.get('val2')
        if val2 is "":
            val2 = "0"
        val3 = request.forms.get('val3')
        if val3 is "":
            val3 = "0"
        val4 = request.forms.get('val4')
        if val4 is "":
            val4 = "0"
        val5 = request.forms.get('val5')
        if val5 is "":
            val5 = "0"
        balance.set_new_balance(val1, val2, val3, val4, val5)
        return template('tnx', balance = balance)

    run(host='0.0.0.0', port=argv[1], server='gunicorn')

if __name__ == "__main__":
    main()
