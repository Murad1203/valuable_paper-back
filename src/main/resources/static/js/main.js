
const OPTIONS = {
    // Параметры шкал диаграммы.
    scales: {
        yAxes: [{
            // Линии сетки.
            gridLines: {
                // Отображение линии.
                display: true
            }
        }],
        xAxes: [{
            gridLines: {
                display: true
            },
            time: {
                // Формат отображения временной шкалы:
                displayFormats: {
                    // почасовая: 'День/Месяц часы:минуты'.
                    hour: 'YYYY/mm/dd'
                }
            }
        }]
    },
    // Легенда диаграммы.
    legend: {
        // Отображение легенды.
        display: true
    },
    // Отзывчивость. Растягивание
    // по ширине родительского контейнера.
    responsive: true,
    // Поддерживать соотношение сторон
    // диаграммы при отображении.
    maintainAspectRatio: false
}

Vue.component('line-chart', {
    extends: VueChartJs.Line,
    mixins: VueChartJs.mixins.reactiveProp,
    props: ['chartDate', 'chartPrice'],
    mounted () {
        this.render()
    },

    methods: {
        render() {
            this.renderChart({
                labels: this.$props.chartDate,
                datasets: [
                    {
                        label: 'Инструмент',
                        backgroundColor: '#f87979',
                        data: this.$props.chartPrice
                    }
                ]
            }, OPTIONS)
        }
    }

})



const API = "http://localhost:8080/papers";

const  app =  new Vue({
    el: '#app',
    data: {
        banks: [],

        chartDate:[],
        chartPrice:[],

        nid:'',
        ndate:'',
        nname: '',
        nprice: '',

        id:'',
        date:'',
        name: '',
        price: ''
    },
    methods: {
        async fetchAPI() {
            let response = await fetch(API);
            this.banks = await response.json()
            // this.getData();
        },
        async fethPostApi() {

            const response = await fetch(API, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    'name': this.name,
                    'price':this.price

                })

            })
                .then(response => {
                    this.fetchAPI()
                    this.name = '';
                    this.price = '';
                })
            if(response.status !== 201) {
                console.error('error')
            }

            // const result = await response.json();
            // this.banks.push(result);

        },
        async fetchPutApi() {
            const response = await fetch(API + '/update', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    'id': this.nid,
                    'date': this.ndate,
                    'name': this.nname,
                    'price':this.nprice
                })

            })
                .then(response => {
                    // this.fetchAPI()
                    this.nid = '';
                    this.ndate = '';
                    this.nname = '';
                    this.nprice = '';

                })

                .catch(e => {
                    console.log(e)
                })


        },
        isElement: function (val, newVal) {

            const self = this;

            for (bank in self.banks) {
                if(
                    val === self.banks[bank].name
                    || val == self.banks[bank].price
                    || val == self.banks[bank].id
                    || val === self.banks[bank].date)
                {
                    self.nid = self.banks[bank].id;
                    self.ndate = self.banks[bank].date;
                    self.nname = self.banks[bank].name;
                    self.nprice = self.banks[bank].price;

                    console.log(newVal)

                    if(val == self.banks[bank].id) self.nid = newVal;
                    if(val === self.banks[bank].date) self.ndate = newVal;
                    if(val == self.banks[bank].name) self.nname = newVal;
                    if(val == self.banks[bank].price) self.nprice = newVal;

                    self.fetchPutApi();

                }
            }

        },
        updatePaper() {

            console.log('sss');
            var self = this;

            let tds = document.querySelectorAll('.tds');
            console.log(tds);
            for(let i = 0; i < tds.length; i++) {
                console.log(tds)
                tds[i].addEventListener('click', function func() {

                    const text = this.innerHTML;


                    //inline
                    let input = document.createElement('input');

                    input.value = this.innerHTML;
                    this.innerHTML = '';
                    this.appendChild(input);


                    let td = this;
                    input.addEventListener("blur", function () {
                        td.innerHTML = this.value;
                        self.isElement(text, this.value);
                    })

                    this.removeEventListener('click', func);
                })


            }
        },
        getData() {
            console.log(this.banks)
            for(bank in this.banks) {
                this.chartDate.push(this.banks[bank].date)
                this.chartPrice.push(this.banks[bank].price);
            }
        }


    },
    created() {
        this.fetchAPI();
    },
    mounted() {
        this.updatePaper();
    },
    update() {

    }
})



