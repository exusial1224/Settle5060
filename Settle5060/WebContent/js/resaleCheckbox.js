$(document).ready(function() {
    $('#resaleForm').on('submit', function(e) {
        let selectedIds = [];
        $('.resale-check:checked').each(function() {
            selectedIds.push($(this).val());
        });

        // チェックがない場合のバリデーション
        if (selectedIds.length === 0) {
            alert('リセール予約するスロットを選択してください。');
            e.preventDefault();
        } else {
            // 動的にhiddenフィールドを作成してフォームに追加
            selectedIds.forEach(function(id) {
                $('<input>').attr({
                    type: 'hidden',
                    name: 'selectedSlotId[]',
                    value: id
                }).appendTo('#resaleForm');
            });
        }
    });
});
