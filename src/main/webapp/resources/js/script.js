function clickFunc(id){
    const btn = document.querySelector("#user_contact" + id);
    btn.classList.toggle("active_block");
};

function readUrl(input) {
    if (input.files) {
        let a = document.getElementById("img");
        let reader = new FileReader();
        reader.readAsDataURL(input.files[0]);
        reader.onload = (e) => {
            a.src = e.target.result;
        }
    }
};