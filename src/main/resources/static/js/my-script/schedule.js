$(document).ready(function () {
    var data = findCoursesByLecturerId();
    console.log(data);
    renderTimeTable(data,1);

});

function findCoursesByLecturerId() {
    var url = $('#urlFindCourses').attr('href');
    var lecturerId = $('#lecturerId').text();
    url+=lecturerId;
    var result = null;
    $.ajax({
        url:url,
        type: 'get',
        async: false,
        contentType: false,
        dataType: 'json',
        success: function (data) {
            result = data;
        },
        error:function (res) {
            console.log('error');
            console.log(res);
        }
    });
    return result;
}

function renderTimeTable(data,week) {
    var timetable = new Timetable();
    timetable.setScope(7,20);
    timetable.addLocations(['Thứ 2', 'Thứ 3', 'Thứ 4', 'Thứ 5', 'Thứ 6', 'Thứ 7', 'Chủ Nhật']);
    $.each(data,function (index,course) {
        var courseName = course.subjectDTO.name;
        var courseId = course.courseId;
        $.each(course.courseDetailDTOS,function (i,courseDetail) {
            if(week>=courseDetail.startWeek && week<=courseDetail.endWeek){
                var date = courseDetail.startDate;
                date = (date!=8) ? date : 'Chủ Nhật';
                var startTime =  courseDetail.startLesson;
                startTime = startTime>=5 ? (startTime+7): (startTime+6);
                var endTime = startTime+ courseDetail.numberOfLessons;
                timetable.addEvent(courseName+' - '+courseId, 'Thứ '+date,
                    new Date(null,null,null,startTime),
                    new Date(null,null,null,endTime));
            }

        });
    });
    var renderer = new Timetable.Renderer(timetable);
    renderer.draw('.timetable');
}

