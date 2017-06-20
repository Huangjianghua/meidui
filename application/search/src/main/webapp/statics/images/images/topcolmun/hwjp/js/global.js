/**
 * Created by Administrator on 2015/11/25 0025.
 */
window.onload=function(){

    var map= document.getElementById('map-content');

    var wid=map.clientWidth;
    var hei=map.clientHeight;
    var themepath="<{$themepath}>";
    console.log("map: height:"+hei+"width:"+wid);
   function off(){
       $('#nvshen').attr('src',themepath+"/images/images/topcolmun/fbsm/images/nvshen.png");
       $('#ouzhou').attr('src',themepath+"/images/images/topcolmun/fbsm/images/ouzhou.png");
       $('#ouzhoufang').attr('src',themepath+"/images/images/topcolmun/fbsm/images/ouzhoufang.png");
       $('#ouzhouta').attr('src',themepath+"/images/images/topcolmun/fbsm/images/ouzhouta.png");
       $('#china').attr('src',themepath+"/images/images/topcolmun/fbsm/images/yazhouta.png");
       $('#yazhoushan').attr('src',themepath+"/images/images/topcolmun/fbsm/images/yazhoushan.png");
       $('#yazhouli').attr('src',themepath+"/images/images/topcolmun/fbsm/images/yazhouli.png");
   }
    var mouseon=false;
    //Ů��
    var nv=true;
    var nvshentime;
    var start=true;
    $('#nvshen ').on('mouseover',function(){
        off();
        $('#nvshen').attr('src',themepath+"/images/images/topcolmun/fbsm/images/nvshen_2.png");
        mouseon=true;
    });
    $('#nvshen').on('mouseout',function(){
        $('#nvshen').attr('src',themepath+"/images/images/topcolmun/fbsm/images/nvshen.png");

        mouseon=false;
    });


    setTimeout(function () {
        setInterval(function(){
            if(mouseon==false){
                if(start){
                    nvshentime = setInterval(function(){
                        if(mouseon==false){
                            if(nv){
                                $('#nvshen').attr('src',themepath+"/images/images/topcolmun/fbsm/images/nvshen.png");
                                nv=false;
                            }else{
                                $('#nvshen').attr('src',themepath+"/images/images/topcolmun/fbsm/images/nvshen_2.png");
                                nv=true;
                            }
                        }
                    },400);
                    start=false;
                }
                else{
                    clearInterval(nvshentime);
                    $('#nvshen').attr('src',themepath+"/images/images/topcolmun/fbsm/images/nvshen.png");
                    start=true;
                }
            }
        },2500) ;
    } ,1000) ;
    //ŷ��
    var oz =true;
    var oztime;
    var ozstart=true;


    $(document).on({
        mouseover: function () {
            off();
            $('#ouzhou').attr('src',themepath+"/images/images/topcolmun/fbsm/images/ouzhou_2.png");
            $('#ouzhoufang').attr('src',themepath+"/images/images/topcolmun/fbsm/images/ouzhoufang_2.png");
            $('#ouzhouta').attr('src',themepath+"/images/images/topcolmun/fbsm/images/ouzhouta_2.png");
            mouseon=true;
        },
        mouseout: function () {
            $('#ouzhou').attr('src',themepath+"/images/images/topcolmun/fbsm/images/ouzhou.png");
            $('#ouzhoufang').attr('src',themepath+"/images/images/topcolmun/fbsm/images/ouzhoufang.png");
            $('#ouzhouta').attr('src',themepath+"/images/images/topcolmun/fbsm/images/ouzhouta.png");
            mouseon=false;
        }
    },'#ouzhou,#ouzhoufang,#ouzhouta');


    setTimeout(function () {
        setInterval(function(){
            if(mouseon==false){
                if(ozstart){
                    oztime = setInterval(function(){
                        if(mouseon==false){
                            if(oz){
                                $('#ouzhou').attr('src',themepath+"/images/images/topcolmun/fbsm/images/ouzhou_2.png");
                                $('#ouzhoufang').attr('src',themepath+"/images/images/topcolmun/fbsm/images/ouzhoufang_2.png");
                                $('#ouzhouta').attr('src',themepath+"/images/images/topcolmun/fbsm/images/ouzhouta_2.png");
                                oz=false;
                            }else{
                                $('#ouzhou').attr('src',themepath+"/images/images/topcolmun/fbsm/images/ouzhou.png");
                                $('#ouzhoufang').attr('src',themepath+"/images/images/topcolmun/fbsm/images/ouzhoufang.png");
                                $('#ouzhouta').attr('src',themepath+"/images/images/topcolmun/fbsm/images/ouzhouta.png");
                                oz=true;
                            }}

                    },400);
                    ozstart=false;
                }
                else{
                    clearInterval(oztime);
                    $('#ouzhou').attr('src',themepath+"/images/images/topcolmun/fbsm/images/ouzhou.png");
                    $('#ouzhoufang').attr('src',themepath+"/images/images/topcolmun/fbsm/images/ouzhoufang.png");
                    $('#ouzhouta').attr('src',themepath+"/images/images/topcolmun/fbsm/images/ouzhouta.png");
                    ozstart=true;
                }
            }
        },2500);
    } ,3000) ;
    //����
    var yz =true;
    var yztime;
    var yzstart=true;


    $(document).on({
        mouseover: function () {
            off();
            $('#china').attr('src',themepath+"/images/images/topcolmun/fbsm/images/yazhouta_2.png");
            $('#yazhoushan').attr('src',themepath+"/images/images/topcolmun/fbsm/images/yazhoushan_2.png");
            $('#yazhouli').attr('src',themepath+"/images/images/topcolmun/fbsm/images/yazhouli_2.png");
            mouseon=true;


        },
        mouseout: function () {
            $('#china').attr('src',themepath+"/images/images/topcolmun/fbsm/images/yazhouta.png");
            $('#yazhoushan').attr('src',themepath+"/images/images/topcolmun/fbsm/images/yazhoushan.png");
            $('#yazhouli').attr('src',themepath+"/images/images/topcolmun/fbsm/images/yazhouli.png");
            mouseon=false;

        }
    },'#yazhoushan,#yazhouli,#china');

  setTimeout(function () {
      setInterval(function(){
          if(mouseon==false){
              if(yzstart){
                  yztime = setInterval(function(){
                      if(mouseon==false){
                          if(yz){
                              $('#china').attr('src',themepath+"/images/images/topcolmun/fbsm/images/yazhouta_2.png");
                              $('#yazhoushan').attr('src',themepath+"/images/images/topcolmun/fbsm/images/yazhoushan_2.png");
                              $('#yazhouli').attr('src',themepath+"/images/images/topcolmun/fbsm/images/yazhouli_2.png");
                              yz=false;
                          }else{
                              $('#china').attr('src',themepath+"/images/images/topcolmun/fbsm/images/yazhouta.png");
                              $('#yazhoushan').attr('src',themepath+"/images/images/topcolmun/fbsm/images/yazhoushan.png");
                              $('#yazhouli').attr('src',themepath+"/images/images/topcolmun/fbsm/images/yazhouli.png");
                              yz=true;
                          }}

                  },400);
                  yzstart=false;
              }
              else{
                  clearInterval(yztime);
                  $('#china').attr('src',themepath+"/images/images/topcolmun/fbsm/images/yazhouta.png");
                  $('#yazhoushan').attr('src',themepath+"/images/images/topcolmun/fbsm/images/yazhoushan.png");
                  $('#yazhouli').attr('src',themepath+"/images/images/topcolmun/fbsm/images/yazhouli.png");
                  yzstart=true;
              }
          }
      },2500);
  } ,5000) ;




    //ͼƬ���Ŵ�
    var idstr='';
    for(var i=1;i<=47;i++){
        idstr+='#a-pulse-'+i+',';
    }
    $(document).on({
        mouseover:function(){
            $(this).addClass('animated pulse infinite');
        },
        mouseout:function(){
            $(this).removeClass('animated pulse infinite');
        }
    },idstr);


    //����
    var oldx=0;
    var oldy=0;

    function  moveobj(){
        var temx=wid*Math.random();
        var tmpy=hei/2*Math.random();

        console.log(temx+"-"+tmpy );
        if(oldx!=0){
            if (temx>oldx ){
                $('.balloon').attr('src',themepath+"/images/images/topcolmun/fbsm/images/balloon_left.png");  //�����
            }else{
                $('.balloon').attr('src',themepath+"/images/images/topcolmun/fbsm/images/balloon_right.png");    //���ҷ�
            }}

        oldx=temx;
        oldy=tmpy;
       // move('.balloon').to(-temx,tmpy).duration('30s').end();
        $(".balloon").stop().animate({"right":temx,"top":tmpy},30000,"linear");
        //$(".balloon").velocity({"right":temx,"top":tmpy},20000);
    };
    moveobj();
    setInterval(moveobj, 20000);


    //��Ÿ
    //move('.bird').to(wid/4*Math.random(),hei/4*Math.random()).duration('10s').end();
    $("#bird").stop().animate({"left":wid/4*Math.random(),"top":hei/4*Math.random()},10000,"linear");
    setInterval(function(){
        $("#bird").stop().animate({"left":wid/4*Math.random(),"top":hei/4*Math.random()},10000,"linear");
        //move('.bird').to(wid/4*Math.random(),hei/4*Math.random()).duration('10s').end();
    },10000);
    $("#bird2").stop().animate({"left":wid/4*Math.random(),"top":hei/4*Math.random()},10000,"linear");
    setInterval(function(){
        $("#bird2").stop().animate({"left":wid/4*Math.random(),"top":hei/4*Math.random()},10000,"linear");
    },8000);
     $("#bird3").stop().animate({"left":wid/4*Math.random(),"top":hei/4*Math.random()},10000,"linear");
    setInterval(function(){
         $("#bird3").stop().animate({"left":wid/4*Math.random(),"top":hei/4*Math.random()},10000,"linear");
    },6000);

    $("#bird4").stop().animate({"left":wid/4*Math.random(),"top":hei/4*Math.random()},10000,"linear");
    setInterval(function(){
        $("#bird4").stop().animate({"left":wid/4*Math.random(),"top":hei/4*Math.random()},10000,"linear");
    },8000);
    var chi=1;
    setInterval(function(){
        if(chi==1){
            $('#bird').attr('src',themepath+"/images/images/topcolmun/fbsm/images/haiou1.png");
            $('#bird4').attr('src',themepath+"/images/images/topcolmun/fbsm/images/haiou3.png");
            chi=2;
        }else if(chi==2){
            $('#bird').attr('src',themepath+"/images/images/topcolmun/fbsm/images/haiou2.png");
            $('#bird4').attr('src',themepath+"/images/images/topcolmun/fbsm/images/haiou2.png");
            chi=3;
        }else{
            $('#bird').attr('src',themepath+"/images/images/topcolmun/fbsm/images/haiou3.png");
            $('#bird4').attr('src',themepath+"/images/images/topcolmun/fbsm/images/haiou1.png");
            chi=1;
        }

    },300);
    var chi2=1;
    setInterval(function(){
        if(chi2==1){
            $('#bird2').attr('src',themepath+"/images/images/topcolmun/fbsm/images/Seagull1.png");
            $('#bird3').attr('src',themepath+"/images/images/topcolmun/fbsm/images/Seagull3.png");
            chi2=2;
        }else if(chi2==2){
            $('#bird2').attr('src',themepath+"/images/images/topcolmun/fbsm/images/Seagull2.png");
            $('#bird3').attr('src',themepath+"/images/images/topcolmun/fbsm/images/Seagull2.png");
            chi2=3;
        }else{
            $('#bird2').attr('src',themepath+"/images/images/topcolmun/fbsm/images/Seagull3.png");
            $('#bird3').attr('src',themepath+"/images/images/topcolmun/fbsm/images/Seagull1.png");
            chi2=1;
        }

    },300);

    //����
    var pu=true;
    setInterval(function(){
        if(pu){
            $('#fish-big').attr('src',themepath+"/images/images/topcolmun/fbsm/images/fish-big.png");
            pu=false;
        }else{
            $('#fish-big').attr('src',themepath+"/images/images/topcolmun/fbsm/images/fish-no.png");
            pu=true;
        }
    },2000);
//���
    var penguin='1';
    setInterval(function(){
        if(penguin=='1'){
            $('#penguin').attr('src',themepath+"/images/images/topcolmun/fbsm/images/penguin1.png");
            penguin='2';
        }else if(penguin=='2'){
            $('#penguin').attr('src',themepath+"/images/images/topcolmun/fbsm/images/penguin2.png");
            penguin='3';
        }else{
            $('#penguin').attr('src',themepath+"/images/images/topcolmun/fbsm/images/penguin3.png");
            penguin='1';
        }

    },200);



    var w=wid/20;
    var arr= [
        [5*w,50],
        [6*w,60],
        [8*w,50],
        [10*w,60],
        [11*w,80],
        [9.8*w,180],
        [11*w,220],
        [10*w,400],
        [12*w,500],
        [13*w,600],
        [13*w,680],
        [15*w,680],
        [17*w,480],
        [15*w,380],
        [15*w,280],
        [17*w,180],
        [17*w,30],
        [14*w,20],
        [12*w,50],
        [11*w,100],
        [9.5*w,180],
        [11*w,220],
        [11*w,400],
        [11*w,470],
        [11*w,550],
        [9*w,680],
        [6.2*w,480],
        [7*w,500],
        [4*w,680],
        [2*w,480],
        [2*w,280],
        [3*w,100],
        [4*w,80],






        //[4*w,0],
        //[5*w,10],
        //[7*w,5],
        //[6*w,100],
        //[7*w,200],
        //[6*w,300],
        //[8*w,350],
        //[8.5*w,450],
        //[9*w,600],
        //[11*w,600],
        //[11*w,500],
        //[12*w,400],
        //[10.5*w,300],
        //[10*w,200],
        //[12*w,100],
        //[13*w,-10],
        //[13*w,-50],
        //[10*w,-50],
        //[8*w,-10],
        //[6*w,-10],
        //[6*w,100],
        //[6*w,200],
        //[6.5*w,300],
        //[6.5*w,350],
        //[6.5*w,500],
        //[5*w,580],
        //[4*w,550],
        //
        //[2*w,350],
        //[3*w,450],
        //[1*w,500],
        //[0,550],
        //[-2*w,400],
        //[-1.5*w,350],
        //[-3*w,350],
        //[-3*w,200],
        //[-1.5*w,100],
        //[-1.5*w,0],
        //[0,0],

    ];
    var i=0;
    var shipdir='right';
    var shipoldx=0;


    function moveship(){
        console.log(i+'-'+arr[i][0]+'-'+arr[i][1]);
        if (shipoldx!=0 ){
            if(shipoldx>=arr[i][0]){
                shipdir='left';
            }
            else{
                shipdir='right';
            }
        }
        //move('.ship').to(arr[i][0],arr[i][1]).duration('20s').end();
        $('.ship').stop().animate({"left":arr[i][0],"top":arr[i][1]},18000,'linear');
        shipoldx = arr[i][0]
        i++;

        if(i>=arr.length){
            i=0;
            shipoldx = 0;
        }
    }
    moveship();
    setInterval(moveship,20000);

    var shipswing=true;

    setInterval(function(){
        if(shipdir=='right'){
            if(shipswing){
                $('.ship').attr('src',themepath+"/images/images/topcolmun/fbsm/images/ship_right_1.png");
                shipswing=false;
            }else{
                $('.ship').attr('src',themepath+"/images/images/topcolmun/fbsm/images/ship_right_2.png");

                shipswing=true;
            }
        }else{
            if(shipswing){
                $('.ship').attr('src',themepath+"/images/images/topcolmun/fbsm/images/ship_left_1.png");
                shipswing=false;
            }else{
                $('.ship').attr('src',themepath+"/images/images/topcolmun/fbsm/images/ship_left_2.png");

                shipswing=true;
            }
        }

    },800);

}
