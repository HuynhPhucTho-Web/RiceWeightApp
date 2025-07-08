<!-- Bootstrap 5 CDN -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

<form action="WeighingServlet" method="post" class="container mt-4">

    <div class="row mb-3">
        <div class="col-md-6">
            <input type="number" name="customerID" class="form-control" placeholder="Mã khách hàng" required>
        </div>
        <div class="col-md-6">
            <input type="number" name="pricePerKg" class="form-control" placeholder="Giá m?i Kg" required>
        </div>
    </div>

    <div class="table-responsive">
        <div id="gridContainer" class="d-flex gap-3 mb-2"></div>
    </div>

    <div class="d-flex gap-3 mb-3" id="columnSums"></div>

    <div class="mb-3">
        <strong class="text-primary fs-5">T?ng t?t c?: <span id="totalWeight">0.0</span> Kg</strong>
    </div>

    <button type="submit" class="btn btn-success">L?u phiên cân</button>
</form>

<!-- JS -->
<script>
    let colCount = 0;
    const maxRows = 5;

    function createColumn() {
        const container = document.getElementById('gridContainer');
        const colIndex = colCount;

        const colDiv = document.createElement('div');
        colDiv.className = 'd-flex flex-column align-items-center column';
        colDiv.id = `column-${colIndex}`;

        const header = document.createElement('div');
        header.innerText = `C${colIndex + 1}`;
        header.className = 'fw-bold text-center mb-2';
        colDiv.appendChild(header);

        for (let i = 0; i < maxRows; i++) {
            const input = document.createElement('input');
            input.type = 'number';
            input.name = 'weightKg';
            input.inputMode = 'decimal';
            input.className = 'form-control mb-2 text-center';
            input.style.width = '70px';
            input.dataset.colIndex = colIndex;
            input.oninput = () => checkColumn(colIndex);
            colDiv.appendChild(input);
        }

        container.appendChild(colDiv);

        // Create sum block
        const sumDiv = document.createElement('div');
        sumDiv.className = 'text-center fw-bold text-danger';
        sumDiv.style.width = '70px';
        sumDiv.id = `sum-${colIndex}`;
        sumDiv.innerText = '0.0';
        document.getElementById('columnSums').appendChild(sumDiv);

        colCount++;
    }

    function checkColumn(colIndex) {
        const inputs = document.querySelectorAll(`#column-${colIndex} input`);
        let filled = 0;
        let sum = 0;

        inputs.forEach(input => {
            const val = parseFloat(input.value);
            if (!isNaN(val)) {
                filled++;
                sum += val;
            }
        });

        document.getElementById(`sum-${colIndex}`).innerText = sum.toFixed(1);

        // N?u ?ây là c?t cu?i cùng và ?ã ?? s? ô ? t?o c?t m?i
        if (filled === maxRows && colIndex === colCount - 1) {
            createColumn();
        }

        updateTotalWeight();
    }

    function updateTotalWeight() {
        let total = 0;
        for (let i = 0; i < colCount; i++) {
            const sumText = document.getElementById(`sum-${i}`)?.innerText || "0";
            const colSum = parseFloat(sumText);
            if (!isNaN(colSum))
                total += colSum;
        }
        document.getElementById('totalWeight').innerText = total.toFixed(1);
    }

    window.onload = () => {
        createColumn(); // Start with 1 column
    };
</script>

