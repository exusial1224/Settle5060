document.addEventListener("DOMContentLoaded", () => {

    const rows = document.querySelectorAll(".selectable-row");

    rows.forEach(row => {
        row.addEventListener("click", (event) => {

            const target = event.target;
            if (target.closest("td") && target.closest("td").cellIndex === 3) {
                return; //11a月 25日： カラム3無視でリセールリセール対応
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

//2/5 リセールのクリック挙動
$(document).ready(function () {
    $(".resale-check").on("change", function () {
        let selectedSlots = $(".resale-check:checked").map(function () {
            return $(this).val();
        }).get(); // 選択されたすべてのスロットIDを配列で取得

        sendToResaleRegister(selectedSlots);
    });

    function sendToResaleRegister(slotIds) {
        $.post("ResaleRegister", { "selectedSlotId[]": slotIds })
            .done(function (response) {
                alert("リセール予約を送信しました");
                location.reload(); // UI更新
            })
            .fail(function () {
                alert("リセール予約に失敗しました");
            });
    }
});
