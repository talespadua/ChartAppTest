from bottle import run, route, request

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


def main():
    balance = Balance()

    @route('/get_balance')
    def get_balance():
        return balance.__dict__

    @route('/update_balance', method='POST')
    def update_balance():
        jan = request.forms.get('val1')
        fev = request.forms.get('val2')
        mar = request.forms.get('val3')
        abr = request.forms.get('val4')
        mai = request.forms.get('val5')
        balance.set_new_balance(val1, val2, val3, val4, val5)
        return balance.__dict__

    run(host='localhost', port=8084)

if __name__ == "__main__":
    main()
