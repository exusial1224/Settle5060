$(document).ready(function () {
        //ポップアップイン
        function showPopup() {
            populatePopupDateSelect(); //ここでリスト更新
            $("#blackout").fadeIn();
            $("#popup").fadeIn();
        }

        //ポップアップアウト
        function hidePopup() {
            $("#blackout").fadeOut();
            $("#popup").fadeOut();
        }

        //日付リスト生成
        function populatePopupDateSelect() {
            const $dateSelect = $("#popupDateSelect");
            $dateSelect.empty();
            const currentDate = new Date();
            const twoMonthsLater = new Date();
            twoMonthsLater.setMonth(currentDate.getMonth() + 2);

            //二か月 青木がDAO変えたら販売開始日参照に変更
            while (currentDate <= twoMonthsLater) {
                const dateValue = currentDate.toISOString().split("T")[0];
                $dateSelect.append(
                    $("<option>").val(dateValue).text(dateValue)
                );
                currentDate.setDate(currentDate.getDate() + 1);
            }
        }

        $("#popupSubmit").on("click", function () {
            const selectedDate = $("#popupDateSelect").val();
            if (!selectedDate) {
                alert("日付を選択してください。");
                return;
            }

            $("#dateInput").val(selectedDate);
            hidePopup();
        });

        $("#popupCancel").on("click", function () {
            hidePopup();
        });

        $("#dateInput").on("click", function () {
            showPopup();
        });
    });