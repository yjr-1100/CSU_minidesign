;(function(){
    window.onload = function(){
        const searchBox = document.querySelector('.searchBox')
        const searchDtn = document.querySelector('.searchbtn')
        const searchCloseBtn = document.querySelector('.closesearchbtn')
        searchDtn.addEventListener('click',function () {
            searchBox.style.display = 'flex';
        })
        searchCloseBtn.addEventListener('click',function () {
            searchBox.style.display = 'none';
        })


        const dds = document.querySelectorAll('.dd')
        const aWithSlipDown = document.querySelectorAll('.top-r>ul>li>a')

        // 列表选项从上而下出现
        let list_flag = true
        let listDown = (obj,startheight,height,callback) => {
            clearInterval(obj.timer);
            obj.style.display = 'block'
            var startHeight = startheight;
            obj.timer = setInterval(function (){
                var step = (height - startHeight)/10;
                step = step >0? Math.ceil(step):Math.floor(step);
                startHeight+=step;
                if (startHeight>= height) {
                    clearInterval(obj.timer);
                }
                obj.style.height = startHeight+ 'px';
            }, 10);
        };

        // 列表选项从下而上消失
        let listUp = (obj,height,callback) => {
            let startHeight = height;
            let stopHeight = 0;
            clearInterval(obj.timer);
            obj.timer = setInterval(function (){
                var step = (startHeight)/10;
                step = step >0? Math.ceil(step):Math.floor(step);
                startHeight -=step;
                obj.style.height = startHeight + 'px';
                if (startHeight <= stopHeight) {
                    clearInterval(obj.timer);
                    obj.style.display = 'none';
                }
                obj.style.height = startHeight+ 'px';
            }, 10);
        };

        for(let i = 1;i<=2;i++){
            aWithSlipDown[i].addEventListener('mouseenter',function () {
                let dd = this.parentNode.querySelector('.dd')
                listDown(dd,0,dd.getAttribute('myheight'))
            })
            aWithSlipDown[i].addEventListener('mouseleave',function () {
                let dd = this.parentNode.querySelector('.dd')
                listUp(dd,dd.getAttribute('myheight'))
            })
        }

        for(let i=0;i<dds.length;i++){
            dds[i].addEventListener('mouseleave',function () {
                listUp(this,this.getAttribute('myheight'))
            })
            dds[i].addEventListener('mouseenter',function () {
                console.log(("sdfsdf"))
                listDown(this,this.style.height,this.getAttribute('myheight'))
            })
        }

        const nav_list = document.querySelectorAll('.nav ul li')
        for(let i=0;i<nav_list.length;i++){
            nav_list[i].addEventListener('mouseenter',function (e) {
                console.log(e)
                    let sub = this.children[1]
                    let mypadding = 20
                    let startpadding = 0
                    clearInterval(sub.timer);
                    sub.style.display = 'block'
                    if(getComputedStyle(sub,null).paddingTop!="20px"){
                        sub.timer = setInterval(function (){
                            let step = (mypadding - startpadding)/10;
                            step = step >0? Math.ceil(step):Math.floor(step);
                            startpadding += step;
                            if (startpadding>= mypadding) {
                                clearInterval(sub.timer);
                            }
                            sub.style.paddingTop = startpadding+ 'px';
                            sub.style.paddingBottom = startpadding+ 'px';
                        },20)
                    }
            })
            nav_list[i].addEventListener('mouseleave',function (e) {
                console.log(e)
                    let sub = this.children[1]
                    let mypadding = 0
                    let startpadding = 30
                    clearInterval(sub.timer);
                    sub.timer = setInterval(function (){
                        let step = (startpadding-mypadding)/10;
                        step = step >0? Math.ceil(step):Math.floor(step);
                        startpadding -= step;
                        if (startpadding <= mypadding) {
                            clearInterval(sub.timer);
                            sub.style.display = 'none'
                        }
                        sub.style.paddingTop = startpadding+ 'px';
                        sub.style.paddingBottom = startpadding+ 'px';
                    },20)
            })
        }

        // 滚动条事件
        head = document.querySelector('.head')
        logo1 = document.querySelector('.logo1')
        logo2 = document.querySelector('.logo2')
        top1 = document.querySelector('.top')
        nav = document.querySelector('.nav')
        console.log(nav)
        var goBack = document.querySelector('.goBack');
        document.addEventListener('scroll',function(){
            console.log(window.pageYOffset);
            if(window.pageYOffset>=200){
                top1.style.display = 'none'
                head.style.height='87px'
                logo1.style.display = 'none'
                logo2.style.display = 'block'
                nav.style.marginTop='15px'
            }else{
                nav.style.marginTop='0'
                top1.style.display = 'flex'
                head.style.height='110px'
                logo2.style.display = 'none'
                logo1.style.display = 'block'
            }
            if(window.pageYOffset>=600){
                goBack.style.display = 'block';
            }else{
                goBack.style.display = 'none';
            }
        })
        goBack.addEventListener('click',function(){
            animate(window,0);
        })
        function animate(obj,target,callback){ 
            // 先清楚以前的定时器，只保留当前一个定时器执行。
            clearInterval(obj.timer);
            obj.timer = setInterval(function(){
                // 步长值卸载定时器的里面,往大取整，避免小数问题
                // var step = Math.ceil((target - obj.offsetLeft)/10 );
                var step = (target - window.pageYOffset)/10;
                
                step = step >0? Math.ceil(step):Math.floor(step);
                
                if(window.pageYOffset ==target){
                    // 停止动画 本质是停止定时器
                    clearInterval(obj.timer);
                    if(callback){
                        callback();
                    }
                }
                // 每次移动一个动态的步长，动态的步长每次减小，达到缓慢停止的效果
                window.scroll(0,window.pageYOffset+step) ;
            },15);
        };

    }
}())
