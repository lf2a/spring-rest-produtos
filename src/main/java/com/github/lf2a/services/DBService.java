package com.github.lf2a.services;

import com.github.lf2a.domain.Categoria;
import com.github.lf2a.domain.Cidade;
import com.github.lf2a.domain.Cliente;
import com.github.lf2a.domain.Endereco;
import com.github.lf2a.domain.Estado;
import com.github.lf2a.domain.ItemPedido;
import com.github.lf2a.domain.Pagamento;
import com.github.lf2a.domain.PagamentoComBoleto;
import com.github.lf2a.domain.PagamentoComCartao;
import com.github.lf2a.domain.Pedido;
import com.github.lf2a.domain.Produto;
import com.github.lf2a.domain.enums.EstadoPagamento;
import com.github.lf2a.domain.enums.TipoCliente;
import com.github.lf2a.repositories.CategoriaRepository;
import com.github.lf2a.repositories.CidadeRepository;
import com.github.lf2a.repositories.ClienteRepository;
import com.github.lf2a.repositories.EnderecoRepository;
import com.github.lf2a.repositories.EstadoRepository;
import com.github.lf2a.repositories.ItemPedidoRepository;
import com.github.lf2a.repositories.PagamentoRepository;
import com.github.lf2a.repositories.PedidoRepository;
import com.github.lf2a.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

/**
 * <h1>DBService.java</h1>
 * ---
 *
 * @author Luiz Filipy
 * @version 1.0
 * @since 19/03/2021
 */
@Service
public class DBService {

    @Autowired
    private CategoriaRepository repo;

    @Autowired
    private ProdutoRepository repoProd;

    @Autowired
    private EstadoRepository repoEst;

    @Autowired
    private CidadeRepository repoCid;

    @Autowired
    private EnderecoRepository repoEnd;

    @Autowired
    private ClienteRepository repoCli;

    @Autowired
    private PedidoRepository repoPed;

    @Autowired
    private PagamentoRepository repoPag;

    @Autowired
    private ItemPedidoRepository repoItemPed;

    public void instantiateTestDatabase() throws ParseException {
        Categoria cat1 = new Categoria(null, "Informática");
        Categoria cat2 = new Categoria(null, "Escritório");
        Categoria cat3 = new Categoria(null, "Cama, mesa e banho");
        Categoria cat4 = new Categoria(null, "Eletrônicos");
        Categoria cat5 = new Categoria(null, "Jardinagem");
        Categoria cat6 = new Categoria(null, "Decoração");
        Categoria cat7 = new Categoria(null, "Perfumaria");

        Produto prod1 = new Produto(null, "Computador", 2000.00);
        Produto prod2 = new Produto(null, "Impressora", 800.00);
        Produto prod3 = new Produto(null, "Mouse", 80.00);
        Produto prod4 = new Produto(null, "Mesa de escritorio", 300.00);
        Produto prod5 = new Produto(null, "Toalha", 50.00);
        Produto prod6 = new Produto(null, "Colcha", 200.00);
        Produto prod7 = new Produto(null, "TV true clor", 1200.00);
        Produto prod8 = new Produto(null, "Roçadeira", 800.00);
        Produto prod9 = new Produto(null, "Abajour", 100.00);
        Produto prod10 = new Produto(null, "Pendente", 180.00);
        Produto prod11 = new Produto(null, "Shampoo", 90.00);

        cat1.getProdutos().addAll(Arrays.asList(prod1, prod2, prod3));
        cat2.getProdutos().addAll(Arrays.asList(prod2, prod4));
        cat3.getProdutos().addAll(Arrays.asList(prod5, prod6));
        cat4.getProdutos().addAll(Arrays.asList(prod1, prod2, prod3, prod7));
        cat5.getProdutos().addAll(Arrays.asList(prod8));
        cat6.getProdutos().addAll(Arrays.asList(prod9, prod10));
        cat7.getProdutos().addAll(Arrays.asList(prod11));

        prod1.getCategorias().addAll(Arrays.asList(cat1, cat4));
        prod2.getCategorias().addAll(Arrays.asList(cat1, cat2, cat4));
        prod3.getCategorias().addAll(Arrays.asList(cat1, cat4));
        prod4.getCategorias().addAll(Arrays.asList(cat2));
        prod5.getCategorias().addAll(Arrays.asList(cat3));
        prod6.getCategorias().addAll(Arrays.asList(cat3));
        prod7.getCategorias().addAll(Arrays.asList(cat4));
        prod8.getCategorias().addAll(Arrays.asList(cat5));
        prod9.getCategorias().addAll(Arrays.asList(cat6));
        prod10.getCategorias().addAll(Arrays.asList(cat6));
        prod11.getCategorias().addAll(Arrays.asList(cat7));

        repo.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
        repoProd.saveAll(Arrays.asList(prod1, prod2, prod3, prod4, prod5, prod6, prod7, prod8, prod9, prod10, prod11));

        Estado est1 = new Estado(null, "Minas Gerais");
        Estado est2 = new Estado(null, "São Paulo");

        Cidade c1 = new Cidade(null, "Uberlandia", est1);
        Cidade c2 = new Cidade(null, "São Paulo", est2);
        Cidade c3 = new Cidade(null, "Campinas", est2);

        est1.getCidades().addAll(Arrays.asList(c1));
        est2.getCidades().addAll(Arrays.asList(c2, c3));

        repoEst.saveAll(Arrays.asList(est1, est2));
        repoCid.saveAll(Arrays.asList(c1, c2, c3));

        Cliente cli1 = new Cliente(null, "Maria Silva", "luizfilipy014@gmail.com", "000.000.000-00", TipoCliente.PESSOAFISICA);
        cli1.getTelefones().addAll(Arrays.asList("1234-5678", "8765-4321"));

        Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "34567-856", cli1, c1);
        Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "23454-556", cli1, c2);

        cli1.getEnderecos().addAll(Arrays.asList(e1, e2));

        repoCli.save(cli1);
        repoEnd.saveAll(Arrays.asList(e1, e2));

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
        Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);

        Pagamento pag1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
        ped1.setPagamento(pag1);
        Pagamento pag2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
        ped2.setPagamento(pag2);

        cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

        repoPed.saveAll(Arrays.asList(ped1, ped2));
        repoPag.saveAll(Arrays.asList(pag1, pag2));

        ItemPedido ip1 = new ItemPedido(ped1, prod1, 0.00, 1, 2000.00);
        ItemPedido ip2 = new ItemPedido(ped1, prod3, 0.00, 2, 80.00);
        ItemPedido ip3 = new ItemPedido(ped2, prod2, 100.00, 1, 800.00);

        ped1.getItens().addAll(Arrays.asList(ip1, ip2));
        ped2.getItens().addAll(Arrays.asList(ip3));

        prod1.getItens().addAll(Arrays.asList(ip1));
        prod2.getItens().addAll(Arrays.asList(ip3));
        prod3.getItens().addAll(Arrays.asList(ip2));

        repoItemPed.saveAll(Arrays.asList(ip1, ip2, ip3));
    }
}
