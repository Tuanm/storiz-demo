let tags = document.getElementsByName('tags');

tags.onblur = function() {
    console.log('left tags-field');
};

tags.onfocus = function() {
    console.log('focused tags-field');
}