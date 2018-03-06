angular.module('CapstoneSystem')
    .controller('ProjectsController', ['$scope', function ($scope) {
        var table = $('#projectsTable').DataTable( {
            responsive: true
        } );
        $('.programList').select2({
            placeholder: 'Select program(s)'
        });
        $('#projectsTable tbody').on('click', 'tr', function () {
            var data = table.row( this ).data();
            window.location.href = '/project';
        } );
        $scope.myHtml = "<h5>Project Description...</h5>"
        $scope.froalaOptions = {
            toolbarButtons: ['bold', 'italic', 'underline', 'strikeThrough', 'subscript', 'superscript', '|', 'fontFamily', 'fontSize', 'color', 'inlineStyle', 'paragraphStyle', '|', 'paragraphFormat', 'align', 'formatOL', 'formatUL', 'outdent', 'indent', 'quote', '-', 'insertLink', 'insertImage', 'insertVideo', 'insertFile', 'insertTable', '|', 'emoticons', 'specialCharacters', 'insertHR', 'selectAll', 'clearFormatting', '|', 'print', 'help', 'html', '|', 'undo', 'redo']
        }
    }]);