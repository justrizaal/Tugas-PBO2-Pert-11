<?php 
  include "config/koneksi.php";
  
?>
<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- CSS -->
    <link rel="stylesheet" href="css/lapprint.css">
    <title>LAPORAN PENJUALAN</title>
  </head>
  <body onload="window.print()">
  <div class="container">
    <div class="row">
      <div class="col-md-12"><h1 class="text-center text-primary">LAPORAN PENJUALAN ELEKTRONIK</h1>
        <?php
        $tanggal = date('Y-m-d');
        echo $tanggal;
        ?>
      </div>
    </div>
    <br>
    <form method="post" class="form-inline justify-content-center form-dates">
        <div class="form-group mx-sm-3">
          <label for="dari" class="mr-2">Dari Tanggal</label>
          <input type="date" id="dari" name="dari" class="form-control">
        </div>
        <div class="form-group mx-sm-3">
          <label for="sampai" class="mr-2">Sampai</label>
          <input type="date" id="sampai" name="sampai" class="form-control">
        </div>
        <button type="submit" name="cek" class="btn btn-primary">CEK</button>
    </form>
    <br>
    <div class="row">
      <div class="col-md-12">
        <table class="table">
            <thead class="thead-dark">
                <tr>
                    <th>No Faktur</th>
                    <th>Kode Barang</th>
                    <th>Nama Barang</th>
                    <th>Harga Satuan</th>
                    <th>Jumlah Beli</th>
                    <th>Total</th>
                    <th>Tanggal</th>
                </tr>
            </thead>

            <tbody>
              <?php 
                if (isset($_POST['cek'])) {
                    $dari = $_POST['dari'];
                    $sampai = $_POST['sampai'];

                    // Menambahkan kondisi untuk filtering berdasarkan tanggal
                    $sql = "SELECT * FROM tbl_penjualan WHERE tanggal BETWEEN '$dari' AND '$sampai' ORDER BY tanggal ASC";
                } else {
                    $sql = "SELECT * FROM tbl_penjualan ORDER BY tanggal ASC";
                }

                $query = mysqli_query($con, $sql);
                while($r = mysqli_fetch_array($query)){
              ?>
                <tr>
                    <td><?php echo $r['nofaktur'] ?></td>
                    <td><?php echo $r['kd_barang'] ?></td>
                    <td><?php echo $r['nama_barang'] ?></td>
                    <td><?php echo $r['hsatuan'] ?></td>
                    <td><?php echo $r['jumlah_jual'] ?></td>
                    <td><?php echo $r['harga'] ?></td>
                    <td><?php echo $r['tanggal'] ?></td>
                </tr>
                <?php } ?>
                
                
            </tbody>
        </table>

      </div>
    </div>

  </div>
    <script src="js/bootstrap.min.js"></script>
  </body>
</html>
