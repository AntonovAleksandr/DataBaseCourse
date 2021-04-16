function GetScreen() {
    html2canvas(document.querySelector("body")).then(canvas => {
        var data = canvas.toDataURL('image/png').replace(/data:image\/png;base64,/, '');
        // $.post('saveCPic.php',{data:data}, function(rep){
        //     alert('Изображение '+rep+' сохранено' );
        // });
        console.log(data);
        // document.body.appendChild(canvas);
        // var data = canvas.toDataURL('image/png').replace(/data:image\/png;base64,/, '');
        // console.log(data);
    });
}


document.addEventListener("DOMContentLoaded", GetScreen)