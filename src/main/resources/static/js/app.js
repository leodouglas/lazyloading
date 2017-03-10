$(function () {

    $("#process").click(function (e) {
        if ($('#identificationCard').val() === '') {
            alert('The ID number is required');
            e.preventDefault();
        }
        if ($('#upload-file-info').html() === '') {
            alert('The file is required');
            e.preventDefault();
        }
    });

    $("#frmPlainText").submit(function (e) {
        e.preventDefault();

        $.ajax({
            url: '/',
            method: 'POST',
            contentType: 'text/plain',
            data: $('#input').val()
        }).success(function (data) {
            $('#output').val(data);
        });
    });

});