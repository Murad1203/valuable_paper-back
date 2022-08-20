let tds = document.querySelectorAll('td');
console.log("s");
for (let i = 0; i < tds.length; i++) {
    tds[i].addEventListener('click', function func() {
        var input = document.createElement('input');
        input.value = this.innerHTML;
        this.innerHTML = '';
        this.appendChild(input);

        let td = this;
        input.addEventListener("blur", function () {
            td.innerHTML = this.value;
        })

        this.removeEventListener('click', func);
    });
}




// const url =  "http://localhost:8080/save";
//
// function sendRequest(method, url, body = null) {
//     console.log(body);
//     return new Promise((resolve, reject) => {
//         xhr = new XMLHttpRequest();
//
//         xhr.open(method, url, true);
//
//         xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
//
//
//         xhr.onload = () => {
//             if (xhr.status > 400) {
//                 reject(xhr.response);
//             } else  {
//                 resolve(xhr.response);
//             }
//         }
//         xhr.send(body);
//     });
//
// }
//
// var json = JSON.stringify({
//     name: "NFT",
//     price: 1300
// });
//
//
// sendRequest('POST', url, json)
//     .then(data => console.log(data));
