document.addEventListener("DOMContentLoaded", () => {

    const rows = document.querySelectorAll(".selectable-row");

    rows.forEach(row => {
        row.addEventListener("click", (event) => {

            const target = event.target;
            if (target.closest("td") && target.closest("td").cellIndex === 3) {
                return; //11月 25日： カラム3無視でリセールリセール対応
            }

            //ここで取得
            const slotId = row.dataset.slotId;
            const hiddenInput = document.getElementById("selectedSlotId");
            hiddenInput.value = slotId;

            //送信、そのまま送信すると非対応
            document.getElementById("slotForm").submit();
        });
    });
});
