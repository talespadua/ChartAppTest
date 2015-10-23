from bottle import run, route, request

class Balance():
    def __init__(self):
        self.jan = 230.0
        self.feb = -120.0
        self.mar = 400.0
        self.abr = 350.0
        self.mai = 500
        self.jun = 110

    def set_new_balance(self, jan, feb, mar, abr, mai, jun):
        self.jan = jan
        self.feb = feb
        self.mar = mar
        self.abr = abr
        self.mai = mai
        self.jun = jun

def main():
    balance = Balance()

    @route('/get_balance')
    def get_balance():
        return balance.__dict__

    @route('/update_balance', method='POST')
    def update_balance():
        jan = request.forms.get('jan')
        fev = request.forms.get('feb')
        mar = request.forms.get('mar')
        abr = request.forms.get('abr')
        mai = request.forms.get('mai')
        jun = request.forms.get('jun')
        balance.set_new_balance(jan, fev, mar, abr, mai, jun)
        return balance.__dict__

    run(host='localhost', port=8084)

if __name__ == "__main__":
    main()
