$('#find-courses-table').on('change','[type=checkbox]',function (e) {
    var checkboxClicked = $(this);
    var id = checkboxClicked.attr('course-id');
    if(checkboxClicked.prop('checked')===true){
        var course = findCourseById(id);
        var isRegistered = isRegisteredCourse(course);
        console.log('Is course registered? -->'+isRegistered);
        if(isRegistered===true){
            Swal.fire({
                type: 'error',
                title: 'Môn học này đã có giảng viên đăng ký'
            }).then((result)=>{
                if (checkboxClicked.prop('checked')===true) checkboxClicked.prop('checked',false);
            });
        }
        else if(isInvalidCourse(course)){
            Swal.fire({
                type: 'error',
                title: 'Môn học bị trùng lịch học'
            }).then((result)=>{
                if (checkboxClicked.prop('checked')===true) checkboxClicked.prop('checked',false);
            });
        }
        else{
            addItemToStorage(course);
        }
    }
    else{
        removeItemToStorage(id);
    }

});

function isRegisteredCourse(course) {
    var result = false;
    if(course.registeredAt!=null) result=true;
    return result;
}

function findCourseById(id) {
    var url = searchForm.attr('action')+id;
    var result = null;
    $.ajax({
        url: url,
        type:'get',
        async: false,
        contentType: false,
        dataType: 'json',
        data: id,
        success:function (res) {
            result = res;
        },
        error: function (res) {
            console.log(res);
        }
    });
    return result;
}

function addItemToStorage(course) {
    sessionStorage.setItem(course.id,JSON.stringify(course));
}

function removeItemToStorage(id) {
    sessionStorage.removeItem(id);
}

function isInvalidCourse(course) {
    for(var i=0;i<sessionStorage.length;i++){
        var item = sessionStorage.getItem(sessionStorage.key(i));
        item = JSON.parse(item);
        if(isCoincidenceCourse(item,course)){
            return true;
        }
    }
    return false;
}

function isCoincidenceCourse(course,newCourse) {
    var result = false;
    $.each(course.courseDetailDTOS,function (index,courseDetail) {
        var startDate = courseDetail.startDate;
        var startLesson = courseDetail.startLesson;
        var numberOfLessons = courseDetail.numberOfLessons;
        var startWeek = courseDetail.startWeek;
        var endWeek = courseDetail.endWeek;
        $.each(newCourse.courseDetailDTOS,function (i,newCourseDetail) {
            var startLesson1 = newCourseDetail.startLesson;
            var startDate1 = newCourseDetail.startDate;
            var numberOfLessons1 = newCourseDetail.numberOfLessons;
            var startWeek1 = newCourseDetail.startWeek;
            var endWeek1 = newCourseDetail.endWeek;
            if(startDate===startDate1 &&
                isCoincidenceLesson(startLesson,numberOfLessons,startLesson1,numberOfLessons1) &&
                isCoincidenceWeek(startWeek,endWeek,startWeek1,endWeek1)){
                    result = true;
                    console.log('Trùng lịch học với:'+ course.courseId+'/'+course.subjectDTO.subjectId);
                    return result;
            }
        });
        if(result) return result;
    });
    return result;
}

function isCoincidenceLesson(startLesson,numberOfLesson,startLesson1,numberOfLesson1) {
    var checkArray = [0,0,0,0,0,0,0,0,0,0,0,0];
    for(var i=startLesson-1;i<startLesson+numberOfLesson-1;i++){
        checkArray[i] = 1;
    }
    for(var j=startLesson1-1;j<startLesson1+numberOfLesson1-1;j++){
        if(checkArray[j]==1){
            return true;
        }
        else checkArray[j] = 1;
    }
    return false;
}

function isCoincidenceWeek(startWeek,endWeek,startWeek1,endWeek1) {
    for(var i=startWeek;i<=endWeek;i++){
        if(i>=startWeek1 && i<=endWeek1){
            return true;
        }
    }
    return false;
}

