var deleteArray = [];
var btnDelete = $('#btnDelete');
$('#registerd-courses-table').on('click','input[type=checkbox]',function (e) {
    var isChecked = e.target.checked === true;
    var courseId = e.currentTarget.attributes[0].value;
    if(isChecked){
        deleteArray.push(courseId);
    }
    else{
        deleteArray.splice(deleteArray.indexOf(courseId),1);
    }
    console.log(deleteArray);

    //check array if length=0 then disable button
    if (deleteArray.length===0){
        btnDelete.prop('disabled',true);
    }
    else{
        btnDelete.prop('disabled',false);
    }
});

btnDelete.on('click',function () {
    var url = $('#urlCancel').attr('href');
    var lecturerId = $('#lecturer-id').text();
    url+=lecturerId;
    Swal.fire({
        title: 'Chắc chắn xóa?',
        type: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#d33',
        cancelButtonColor: '#3085d6',
        confirmButtonText: 'Xóa!',
        cancelButtonText: 'Hủy bỏ'
    }).then((result) => {
        if (result.value) {
            $.ajax({
                url: url,
                type: "PUT",
                contentType: "application/json",
                dataType: "text",
                data: JSON.stringify(deleteArray),
                success: function (res) {
                    Swal.fire({
                        type: 'success',
                        title: 'Hủy đăng ký thành công '+res+' môn học trong CSDL',
                        showConfirmButton: false,
                        timer: 2000,
                        allowOutsideClick: false,
                        allowEscapeKey:false,
                        allowOutsideClick: false
                    }).then(()=>{
                        var urlPage = $('#urlPage').attr('href');
                        $(location).attr('href',urlPage);
                    });
                },
                error: function (res) {
                    console.log(res);
                }
            })
        }
    })
});

$('#register-block').on('click',function () {
    deleteArray=[];
    btnDelete.prop('disabled',true);
});