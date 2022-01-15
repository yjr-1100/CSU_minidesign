;(function(){
    let index = 0;
    let imglis = document.querySelector('.banner').querySelector('ul').querySelectorAll('li')
    let btnlis = document.querySelector('.banner').querySelector('ol').querySelectorAll('li')
    let timer = null
    for(let i = 0;i<imglis.length;i++){
        imglis[i].addEventListener('mouseenter',function () {
            clearInterval(timer)
            timer=null
        })
        imglis[i].addEventListener('mouseleave',function () {
            timer = setInterval(function () {
                nextMove();
            },3000)
        })
    }
    for(let i = 0;i<btnlis.length;i++){
        // btnlis[i].index = i;
        btnlis[i].addEventListener('click',function () {
            clearInterval(timer)
            timer = null
            if (i == 0){
                index = 3
            }else{
                index = i-1
            }
            nextMove()
        })
    }
    let moveImg = function (index) {
        for(let i = 0;i<imglis.length;i++){
            if(imglis[i].className=="opc_on") {
                imglis[i].className = ''
            }
        }
        imglis[index].className = "opc_on"
    }
    let moveBtn = function (index) {
        for(let i = 0;i<btnlis.length;i++){
            if(btnlis[i].className="activate"){
                btnlis[i].className=""
            }
        }
        btnlis[index].className = "activate"
    }
    let nextMove = function (){
        index++
        // console.log(index)
        if(index>3){
            index = 0;
        }
        moveImg(index)
        moveBtn(index)
    }

    timer = setInterval(function () {
        nextMove();
    },3000)
}())
