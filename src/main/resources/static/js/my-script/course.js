$(document).ready(function () {
    $('#subjectId').focus();
    var lecturerId = $('#lecturer-id').text();
    sessionStorage.clear();
    addRegisteredCoursesToStorage(lecturerId);
});

var searchForm = $('#search-course-form');
searchForm.on('submit',function (e) {
    e.preventDefault();
    var subjectId = $('#subjectId').val();
    findCoursesBySubjectId(subjectId)
});

var selectSubjectForm = $('#select-subject-form');
selectSubjectForm.find('option').on('click',function (e) {
    var subject = e.target.text.split('-');
    var subjectId = subject[0].trim();
   findCoursesBySubjectId(subjectId);
});

var tbody = $('#find-courses-table');
function findCoursesBySubjectId(subjectId) {
    var url = searchForm.attr('action')+'subjects/'+subjectId;
    $.ajax({
        url:url,
        type: 'get',
        data: subjectId,
        contentType: false,
        dataType: 'json',
        beforeSend: function(){
          tbody.html('');
        },
        success: function (data) {
            var rows = drawItems(data);
            tbody.html(rows);
            $('#subjectId').val('');

        },
        error:function (res) {
            console.log('error');
            console.log(res);
        }

    })
}
$('#lecturer-id-block').on('click',function () {
    var table = $('#registerd-courses-table');
    var data = [];
    for(i=0;i<sessionStorage.length;i++){
        var key = sessionStorage.key(i);
        var value = sessionStorage.getItem(key);
        data[i] = JSON.parse(value);
    }
    table.html(drawItemsForRegisteredCoursesTable(data));
});

function findCoursesByLecturerId(lecturerId) {
    var url = searchForm.attr('action')+'lecturers/'+lecturerId;
    var result = null;
    $.ajax({
        url:url,
        type: 'get',
        async: false,
        data: lecturerId,
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

function addRegisteredCoursesToStorage(lecturerId) {
    var courses = findCoursesByLecturerId(lecturerId);
    $.each(courses,function (index,course) {
        sessionStorage.setItem(course.id,JSON.stringify(course));
    })
}

function drawItemsForRegisteredCoursesTable(data) {
    var rows = '';
    $.each(data,function (index,course) {
        //save data to storage
        var temp = JSON.stringify(course);
        sessionStorage.setItem(course.id,temp);
        //draw
        var subjectId = '<th>'+course.subjectDTO.subjectId+'</th>';
        var subjectName = '<td>'+course.subjectDTO.name+'</td>';
        var courseId = '<td>'+course.courseId+'</td>';
        var credit = '<td>'+course.subjectDTO.credit+'</td>';
        var status = course.registeredAt!=null ? '<td class="text-center">\n' +
            '   <span class="badge" style="background-color: #5cb85c">Đã lưu CSDL</span>\n' +
            ' </td>' : '<td class="text-center">\n' +
            '   <span class="badge">Chưa lưu vào CSDL</span>\n' +
            ' </td>';
        var action = '<td class="text-center"><a><i class="fas fa-trash"></i></a></td>';
        var row = '<tr>' +
            subjectId +
            subjectName+
            courseId+
            credit+
            status+
            action+
            '</tr>';
        rows+=row;
    });
    return rows;
}
function drawItems(data) {
    var rows = '';
    $.each(data, function (i,course) {
        //draw row
        var numberOfCourseDetail = course.courseDetailDTOS.length;
        var row ='';
        $.each(course.courseDetailDTOS,function (index,courseDetail) {
            var checkboxRow = '<td  rowspan="'+numberOfCourseDetail+'">\n' +
                '                            <input course-id="'+course.id+'" type="checkbox"';
            if(course.registeredAt!=null) checkboxRow+=' disabled';
            else if (sessionStorage.getItem(course.id)!=null) checkboxRow+=' checked';
            checkboxRow+='/></td>';
            var statusRow = course.registeredAt ==null? ' <td class="text-center" rowspan="'+numberOfCourseDetail+'">\n' +
                '                            <span class="badge" style="background-color: #5cb85c">Được đăng ký</span>\n' +
                '                        </td>' : ' <td class="text-center" rowspan="'+numberOfCourseDetail+'">\n' +
                '                            <span class="badge">Đã có giảng viên đăng ký</span>\n' +
                '                        </td>';
            var subjectId = '<th scope="row" rowspan="'+numberOfCourseDetail+'">'+course.subjectDTO.subjectId+'</th>';
            var subjectName = '<td rowspan="'+numberOfCourseDetail+'">'+course.subjectDTO.name+'</td>';
            var courseId = '<td rowspan="'+numberOfCourseDetail+'">'+course.courseId+'</td>';
            var credit = '<td rowspan="'+numberOfCourseDetail+'">'+course.subjectDTO.credit+'</td>';
            var startDate ='<td>'+courseDetail.startDate+'</td>';
            var startLesson ='<td>'+courseDetail.startLesson+'</td>';
            var numberOfLesson ='<td>'+courseDetail.numberOfLessons+'</td>';
            var week = '<td>';
            for(i = 1;i<=16;i++){
                if(i>=courseDetail.startWeek && i<=courseDetail.endWeek) week+=(i%10);
                else week+='_';
            }
            week+='</td>';
            if(index==0){
                row += ('<tr>' +
                    checkboxRow +
                    subjectId +
                    subjectName +
                    courseId +
                    credit +
                    startDate +
                    startLesson +
                    numberOfLesson +
                    week+
                    statusRow +'</tr>');
            }
            else{
                row += ('<tr>' +
                    startDate +
                    startLesson +
                    numberOfLesson +
                    week+ '</tr>');
            }
        });
        rows+=row;
    });
    return rows;
}