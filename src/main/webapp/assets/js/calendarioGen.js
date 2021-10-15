/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function(){
    $('#ProgramacionGen').fullCalendar({
        header:{
            left:'today,prev,next',
            center: 'title',
            right:'month, basicWeek'
        },
        
        
        dayClick: function(date, jsEvent, view) {
        alert("valor seleccionado"+date.format());
        },
        events:
                "controlGenerador?action=programaListaGenera"
                
        ,
  
    });
    
});




