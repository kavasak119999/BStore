// $("btn-secondary").click(function () {
//     $(".exampleModalCenter").removeClass("show")
// });

$(document).on("click", ".open-AddBookDialog", function () {
    var image = $(this).data('image');
    $(".modal-body #base64String").val( image );
});

$(function () {
    var includes = $('[data-include]')
    $.each(includes, function () {
        var file = 'templates/layouts/' + $(this).data('include') + '.html'
        $(this).load(file)
    })
})

$(function () {
    var includes = $('[data-included]')
    $.each(includes, function () {
        var file = 'templates/partial/' + $(this).data('include') + '.html'
        $(this).load(file)
    })
})

function showMessage() {
    alert("Thank you! We’ll call you back about a delivery address.");
}