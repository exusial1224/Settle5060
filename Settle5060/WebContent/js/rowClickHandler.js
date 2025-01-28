document.addEventListener("DOMContentLoaded", () => {
    // 行クリックイベントの設定
    const rows = document.querySelectorAll(".selectable-row");

    rows.forEach(row => {
        row.addEventListener("click", (event) => {
            // クリックされた要素が「リセール予約」カラム内の場合は何もしない
            const target = event.target;
            if (target.closest("td") && target.closest("td").cellIndex === 3) {
                return; // 3番目のカラムは無視（0始まりのインデックス）
            }

            const slotId = row.dataset.slotId; // data-slot-id 属性の取得
            const hiddenInput = document.getElementById("selectedSlotId");
            hiddenInput.value = slotId;

            // フォームを送信
            document.getElementById("slotForm").submit();
        });
    });
});
