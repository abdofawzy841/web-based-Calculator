
<template>

      <div class= "main"  >
          
       <div  >
        <button class="textview" :style="styles">{{ myString || 0}}</button>
        </div>
        <div class="clearfix">
            <div class="col_3"><input type="button" value="&#37;" class="button top" @click="percent"></div>
            <div class="col_3"><input type="button" value="CE" class="button top" @click="clear"></div>
            <div class="col_3"><input type="button" value="c" class="button top" @click="clear"></div>
            <div class="col_3"><input type="button" value="Del" class="button top" @click="del"></div>
        </div>
        <div class="clearfix">
            <div class="col_3"><input type="button" value="1/x" class="button top" @click="frac"></div>
            <div class="col_3"><button class="button top" @click="square">x<sup>2</sup></button></div>
            <div class="col_3"><button class="button top"  @click="root"><sup>2</sup>&#8730;x</button></div>
            <div class="col_3"><input type="button" value="&#247;" class="button top"  @click="insert('/')"></div>
        </div>
        <div class="clearfix">
            <div class="col_3"><input type="button" value="7" class="button" @click="insert(7)"></div>
            <div class="col_3"><input type="button" value="8" class="button" @click="insert(8)"></div>
            <div class="col_3"><input type="button" value="9" class="button"  @click="insert(9)"></div>
            <div class="col_3"><input type="button" value="&#215;" class="button top" @click="insert('x')" ></div>
        </div>
        <div class="clearfix">
            <div class="col_3"><input type="button" value="4" class="button" @click="insert(4)"></div>
            <div class="col_3"><input type="button" value="5" class="button" @click="insert(5)"></div>
            <div class="col_3"><input type="button" value="6" class="button" @click="insert(6)"></div>
            <div class="col_3"><input type="button" value="&#8722;" class="button top" @click="insert('-')"></div>
        </div>
        <div class="clearfix">
            <div class="col_3"><input type="button" value="1" class="button" @click="insert(1)" ></div>
            <div class="col_3"><input type="button" value="2" class="button"  @click="insert(2)"></div>
            <div class="col_3"><input type="button" value="3" class="button" @click="insert(3)" ></div>
            <div class="col_3"><input type="button" value="&#43;" class="button top"  @click="insert('+')"></div>
        </div>
        <div class="clearfix">
            <div class="col_3"><input type="button" value="&#43;/&#8722;" class="button" @click="sign"></div>
            <div class="col_3"><input type="button" value="0" class="button"  @click="insert(0)"></div>
            <div class="col_3"><input type="button" value="." class="button" @click="dot"></div>
            <div class="col_3"><input type="button" value="&#61;" class="button top" @click="equal"></div>
        </div>
    </div>
</template>
<script>
import service from "./service";

export default {
    
    data(){
    return{ 
          myString :"",
          lastchar :"",
          //to update the font size
          styles : "",
          equaldone : "",
          oper :"",
        
        }
    },
    methods :{
        insert : function(num){
            this.oper = `${this.myString.charAt(parseFloat(this.myString.length)-2)}`;
            this.lastchar=`${this.myString.charAt(parseFloat(this.myString.length)-1)}`;
            if((this.equaldone==true &&  !isNaN(num) ) ||( this.myString== "error" )){
              this.myString="";
              this.equaldone=false;
            }
           
            if(parseFloat(this.myString.length)<17){
              if(this.myString!="" || !isNaN(num)){
                  this.styles="";
                if(!isNaN(parseFloat(this.lastchar)) || this.lastchar==null || !isNaN(num)){
                    this.myString=`${this.myString}${num}`;
                     this.equaldone=false;
                }
                else if( `${num}`!=`${this.lastchar}`){
                    this.myString=`${this.myString.substring(0,parseFloat(this.myString.length)-1)}${num}`;
                     this.equaldone=false;
                }
              }     
            }   
             this.oper = `${this.myString.charAt(parseFloat(this.myString.length)-2)}`;
            this.lastchar=`${this.myString.charAt(parseFloat(this.myString.length)-1)}`;
               
                },
        sign : function(){
            this.myString=this.myString.charAt(0) === '-' ?
             this.myString.slice(1) :`-${this.myString}`;
        },
        clear : function(){
            this.myString="";
             this.styles="";
        },
        percent : function(){
            if(this.myString!="" && this.myString.indexOf('+') == -1 && this.myString.indexOf('-') == -1 && this.myString.indexOf('/') == -1 && this.myString.indexOf('x') == -1){
           service.retrievepercent(this.myString).then(res => {
               this.myString = `${res.data}`;
               this.equaldone = true;
                if(parseFloat(this.myString.length)>17){
                  this.styles= "font-size: large";
                   }
          
                });
            }
        },
        dot : function(){
            if(this.myString.indexOf('.') == -1){
                this.insert('.');

            }
         },
         del : function(){
             if(this.equaldone || this.myString=="error"){
              this.myString="";
               this.styles="";
             }
            this.myString=`${this.myString.substring(0,parseFloat(this.myString.length)-1)}`;
         },
         equal :function(){
              
            if((this.lastchar!="0" || this.oper!="/")){
             service.retrieveequal(this.myString).then(res => {
               this.myString = `${res.data}`;
               this.equaldone = true;
                if(parseFloat(this.myString.length)>17){
                  this.styles= "font-size: large";
                   }
          
                });
            }
            else{
                this.myString="error"
                   this.equaldone = true;
            }
          
               
            
         },
         frac :function(){
             if(this.myString!="" && this.myString.indexOf('+') == -1 && this.myString.indexOf('-') == -1 && this.myString.indexOf('/') == -1 && this.myString.indexOf('x') == -1 ){
               if(parseFloat(this.myString)!=0){
               service.retrievefrac(this.myString).then(res => {
               this.myString = `${res.data}`;
               this.equaldone = true;
                if(parseFloat(this.myString.length)>17){
                  this.styles= "font-size: large";
                   }
          
                });
                 }
                 else{
                     this.myString="error"
                   this.equaldone = true;
                 }
             }
           }, 
        square:function(){
              if(this.myString!="" && this.myString.indexOf('+') == -1 && this.myString.indexOf('-') == -1 && this.myString.indexOf('/') == -1 && this.myString.indexOf('x') == -1 && parseFloat(this.myString)!=0){
               service.retrievesquare(this.myString).then(res => {
               this.myString = `${res.data}`;
               this.equaldone = true;
                if(parseFloat(this.myString.length)>17){
                  this.styles= "font-size: large";
                   }
          
                });

                }
             },
        root : function(){
            if(this.myString!="" && this.myString.indexOf('+') == -1 && this.myString.indexOf('-') == -1 && this.myString.indexOf('/') == -1 && this.myString.indexOf('x') == -1 && parseFloat(this.myString)!=0){
               service.retrieveroot(this.myString).then(res => {
               this.myString = `${res.data}`;
               this.equaldone = true;
                if(parseFloat(this.myString.length)>17){
                  this.styles= "font-size: large";
                   }
          
                });

                }
           }          

        }
}
</script>
<style >
 *{
      margin: 0;
      padding: 0;
      box-sizing:border-box ;
     
    }
    body{
      background: rgb(93, 94, 91);
    }
    .main{
      background: rgb(10, 10, 9);
      text-align: center;
      width: 400px;
      margin: 80px auto;
      padding: 30px;
      box-shadow: black;
      
    }
    form{
      padding: 0 5px;
      margin-bottom: 15px;
      border-color: rgb(20, 19, 19);
    }
    .textview{
      width: 97%;
      height: 90px;
      padding:  10px;
      pointer-events: none;
      border-color: rgb(15, 15, 15);
      font-size: xx-large;
      background-color: rgb(204, 197, 177);
      text-align: left;
      color: rgb(12, 12, 11);

    }
    .map{
        width: 97%;
        height: 90px;
        padding:  10px;
        pointer-events: none;
        border-color: rgb(15, 15, 15);
        font-size: large;
        background-color: rgb(204, 197, 177);
        text-align: left;
        color: rgb(12, 12, 11);
    }
    .clearfix:before,.clearfix:after{
      content: '';
      display: table;
      clear: both;
      width:  100%;
      
    }
    .col_3{
      width:  25%;
      padding: 5px;
      float: left;
    }  
    .button{
      width:  100%;
      height: 40px;
      outline: none;
      border-color: black;
      font-size: large;
    }
    .button.top{
        background-color: rgb(212, 113, 31);
        color: floralwhite;
        font-size: large;
    }
    .button:hover{
        background-color: rgb(83, 83, 88);
    }
</style>