function back() {
    history.back();
}

function refresh() {
    window.location.reload();
}

var num = 1;

function nextGallery() {
    num++;
    if (num > 3) {
        num = 1;
    } else {
        var photo = document.getElementById("photo");
        photo.src = "/css/photo" + num + ".jpg";
    }
}

function backGallery() {
    num--;
    if (num < 1) {
        num = 3;
    } else {
        var photo = document.getElementById("photo");
        photo.src = "/css/photo" + num + ".jpg";
    }
}

